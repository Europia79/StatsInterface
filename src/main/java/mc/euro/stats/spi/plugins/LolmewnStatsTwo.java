package mc.euro.stats.spi.plugins;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import mc.euro.stats.api.xyz.Data;
import mc.euro.stats.api.xyz.MetaInfo.Context;
import mc.euro.stats.api.xyz.PlayerData;
import mc.euro.stats.api.xyz.StatName;
import mc.euro.stats.spi.Stats;

import org.bukkit.entity.Player;

import nl.lolmewn.stats.api.Stat;
import nl.lolmewn.stats.api.StatDataType;
import nl.lolmewn.stats.api.StatsAPI;
import nl.lolmewn.stats.api.mysql.MySQLAttribute;
import nl.lolmewn.stats.api.mysql.MySQLType;
import nl.lolmewn.stats.api.mysql.StatTableType;
import nl.lolmewn.stats.api.mysql.StatsColumn;
import nl.lolmewn.stats.api.mysql.StatsTable;
import nl.lolmewn.stats.player.StatData;
import nl.lolmewn.stats.player.StatsPlayer;

/**
 * 
 * @author Nikolai
 */
public class LolmewnStatsTwo implements Stats {
    
    StatsAPI api;
    
    public LolmewnStatsTwo(StatsAPI reference) {
        this.api = reference;
    }
    
    @Override
    public void registerStat(mc.euro.stats.api.Stat stat) {
        if (!api.getStatsTableManager().containsKey(stat.getCategory()) ) {
            registerNewTable(stat);
        } else {
            registerNewColumn(stat);
        }
    }
    
    public void registerNewTable(mc.euro.stats.api.Stat stat) {
        String tableName = stat.getCategory();
        StatsTable table = new StatsTable(tableName, true, api.isCreatingSnapshots());
        api.getStatsTableManager().putIfAbsent(tableName, table);
        Stat s = api.addStat(stat.getUniqueId(), 
                StatDataType.INCREASING, 
                StatTableType.TABLE, 
                table, 
                MySQLType.valueOf(stat.getDataType().name()), 
                stat.getName().toLowerCase().replaceAll(" ", ""), 
                stat.getContext().toArray(new String[0])
        );
        table.addStat(s);
        for (Context c : stat.getContext()) {
            StatsColumn col = table.addColumn(c.getName(), MySQLType.valueOf(c.getDataType().name())).addAttributes(MySQLAttribute.NOT_NULL);
            if (c.getDefault() != null) col.setDefault(c.getDefault());
        }
    }
    
    public void registerNewColumn(mc.euro.stats.api.Stat stat) {
        String tableName = stat.getCategory();
        StatsTable table = api.getStatsTableManager().get(tableName);
        api.addStat(stat.getUniqueId(), 
                StatDataType.INCREASING, 
                StatTableType.COLUMN, 
                table, 
                MySQLType.valueOf(stat.getDataType().name()), 
                stat.getName().toLowerCase().replaceAll(" ", ""), 
                stat.getContext().stream().map((Context c) -> c.getName()).toArray((int size) -> new String[size])
        );
    }
    
    @Override
    public void registerStatLog(mc.euro.stats.api.Stat stat) {
        StatsTable table = new StatsTable(stat.getCategory(), true, api.isCreatingSnapshots());
        api.addStat(stat.getUniqueId(), 
                StatDataType.FIXED, 
                StatTableType.TABLE, 
                table, 
                MySQLType.STRING, 
                stat.getName().toLowerCase().replaceAll(" ", ""), 
                stat.getContext().stream().map((Context c) -> c.getName()).toArray((int size) -> new String[size])
        );
    }

    @Override
    public void setData(Player player, mc.euro.stats.api.Stat stat, Data value) {
        Stat s = api.getStat(stat.getUniqueId());
        StatsPlayer splayer = api.getPlayer(player);
        StatData sdata = new StatData(splayer, s, false);
        Object[] contextualValues = new Object[stat.getContext().size()];
        if (value.getContextualValues().values().size() != stat.getContext().size()) {
            throw new UnsupportedOperationException("Not enough metadata was passed to setData()");
        }
        int index = 0;
        for (Context key : stat.getContext()) {
            if (!value.getContextualValues().containsKey(key.getName())) {
                throw new UnsupportedOperationException("Incorrect metadata was passed to setData()");
            }
            contextualValues[index] = value.getContextualValues().get(key.getName());
            index = index + 1;
        }
        
        sdata.setCurrentValue(contextualValues, value.doubleValue());
        sdata.forceUpdate(stat.getContext().toArray());
    }

    @Override
    public void increment(Player player, mc.euro.stats.api.Stat stat) {
        increment(player, stat, 1);
    }

    /**
     * THIS IS WRONG. IT MUST BE REFACTORED !!!
     * This doesn't include the necessary metadata/contextualValues to increment the Stat.
     * Meaning: That the Stat has context: stat.getContext()
     * But the Data doesn't have corresponding metadata: data.getContextualValues()
     */
    @Override
    public void increment(Player player, mc.euro.stats.api.Stat stat, Number amount) {
        Stat s = api.getStatExact(stat.getUniqueId());
        StatsPlayer splayer = api.getPlayer(player);
        StatData data = splayer.getStatData(s, player.getWorld().getName(), true);
        data.addUpdate(new Object[]{}, amount.doubleValue());
    }

    @Override
    public Data getData(Player player, mc.euro.stats.api.Stat stat) {
        StatsPlayer statsPlayer = api.getPlayer(player);
        Stat s = api.getStat(stat.getUniqueId());
        StatData data = statsPlayer.getGlobalStatData(s);
        double value = data.getValue(new Object[] {});
        return new Data(String.valueOf(value));
    }

    @Override
    public Map<StatName, Data> getPlayerStats(Player player) {
        Map<StatName, Data> results = new HashMap<>();
        StatsPlayer splayer = api.getPlayer(player);
        for (Stat s : api.getAllStats()) {
            for (String w : splayer.getWorlds()) {
                StatData d = splayer.getStatData(s, w, false);
                if (d.getAllVariables().isEmpty()) {
                    double v = d.getValue(new Object[] {});
                    StatName stat = new StatName(s.getTable().getName(), s.getName());
                    results.put(stat, new Data(String.valueOf(v)));
                }
            }
        }
        return results;
    }
    
    // @Override public Map<String, Map<String, Data>> getStatCategoriesFor(Player player) { return null }

    @Override
    public Multimap<Integer, PlayerData> getLeaderboard(StatName stat, int size) {
        Multimap<Integer, PlayerData> results = LinkedHashMultimap.create(size * 2, size);
        final String players = api.getDatabasePrefix() + "players";
        final String table = stat.getCategory();
        final String column = stat.getName();
        String format9 = "SELECT %s, %s, %s FROM %s INNER JOIN %s ON %s = %s ORDER BY %s LIMIT %d";
        String sql = String.format(format9, 
                table + ".player_id",   // SELECT table.player_id,
                table + "." + column,   //        table.column,
                players + ".name",      //        players.name
                table,                  // FROM table
                players,                // INNER JOIN players
                table + ".player_id",   // ON table.player_id
                players + ".player_id", // = players.player_id
                table + "." + column,   // ORDER BY table.column
                size);                  // LIMIT size
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = api.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            Integer position = 0;
            String previousValue = null;
            while (rs.next()) {
                rs.getMetaData();
                String player = rs.getString("name");
                String value = rs.getString(stat.getName());
                if (!value.equals(previousValue)) {
                    position = position + 1;
                }
                previousValue = value;
                
                results.put(position, new PlayerData(player, new Data(value)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LolmewnStatsTwo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LolmewnStatsTwo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results;
    }

}

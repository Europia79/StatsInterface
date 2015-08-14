package mc.euro.stats.spi.plugins;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import mc.euro.stats.api.Context.ContextItem;
import mc.euro.stats.api.Data;
import mc.euro.stats.api.DataType;
import mc.euro.stats.api.InvalidDataException;
import mc.euro.stats.spi.Stats;

import org.bukkit.entity.Player;

import nl.lolmewn.stats.api.Stat;
import nl.lolmewn.stats.api.StatDataType;
import nl.lolmewn.stats.api.StatsAPI;
import nl.lolmewn.stats.api.mysql.MySQLAttribute;
import nl.lolmewn.stats.api.mysql.MySQLType;
import nl.lolmewn.stats.api.mysql.StatTableType;
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
        StatsTable table = new StatsTable(stat.getCategory(), true, api.isCreatingSnapshots());
        Stat s = api.addStat(stat.getUniqueId(), 
                StatDataType.INCREASING, 
                StatTableType.TABLE, 
                table, 
                MySQLType.valueOf(stat.getDataType().name()), 
                stat.getName().toLowerCase().replaceAll(" ", ""), 
                stat.getContext().toArray(new String[0])
        );
        table.addStat(s);
        for (ContextItem c : stat.getContext()) {
            table.addColumn(c.getName(), MySQLType.valueOf(c.getDataType().name())).addAttributes(MySQLAttribute.NOT_NULL).setDefault("0");
        }
    }
    
    public void registerNewColumn(mc.euro.stats.api.Stat stat) {
        StatsTable table = new StatsTable(stat.getCategory(), true, api.isCreatingSnapshots());
        api.addStat(stat.getUniqueId(), 
                StatDataType.INCREASING, 
                StatTableType.COLUMN, 
                table, 
                MySQLType.valueOf(stat.getDataType().name()), 
                stat.getName().toLowerCase().replaceAll(" ", ""), 
                stat.getContext().stream().map((ContextItem c) -> c.getName()).toArray((int size) -> new String[size])
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
                stat.getContext().stream().map((ContextItem c) -> c.getName()).toArray((int size) -> new String[size])
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
        for (ContextItem key : stat.getContext()) {
            if (!value.getContextualValues().containsKey(key.getName())) {
                throw new UnsupportedOperationException("Incorrect metadata was passed to setData()");
            }
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
    public Map<mc.euro.stats.api.Stat, Data> getPlayerStats(Player player) {
        Map<mc.euro.stats.api.Stat, Data> results = new HashMap<>();
        StatsPlayer splayer = api.getPlayer(player);
        for (Stat s : api.getAllStats()) {
            for (String w : splayer.getWorlds()) {
                StatData d = splayer.getStatData(s, w, false);
                if (d.getAllVariables().isEmpty()) {
                    double v = d.getValue(new Object[] {});
                    mc.euro.stats.api.Stat stat = new mc.euro.stats.api.Stat(s.getTable().getName(), s.getName());
                    results.put(stat, new Data(String.valueOf(v)));
                }
            }
        }
        return results;
    }

    @Override
    public Map<Integer, Map<String, Data>> getTopStats(mc.euro.stats.api.Stat stat, int top) throws InvalidDataException {
        Map<Integer, Map<String, Data>> results = new HashMap<>();
        String table = stat.getCategory();
        String column = stat.getName();
        String sql = "SELECT * FROM " + table + " ORDER BY " + column + " LIMIT " + top;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = api.getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            Integer position = 0;
            while (rs.next()) {
                position = position + 1;
                String player = rs.getString("player");
                String value = rs.getString(stat.getName());
                Map<String, Data> record = new HashMap<>();
                record.put(player, new Data(value));
                results.put(position, record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LolmewnStatsTwo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LolmewnStatsTwo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (results.isEmpty()) {
            throw new InvalidDataException("No data found");
        }
        return results;
    }

}

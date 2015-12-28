package mc.euro.stats.spi.plugins;


import com.enjin.officialplugin.stats.StatsPlayer;
import com.google.common.collect.Multimap;

import java.util.Map;

import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.DataType;
import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.api.v0.PlayerData;
import mc.euro.stats.spi.v0.Stats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Example Enjin API code:.
 * <pre>
 * StatsPlayer enjinStats = new StatsPlayer(Bukkit.getOfflinePlayer(uuid));
 * enjinStats.addCustomStat("BattleArena", statName, 1, true);
 * </pre>
 * 
 * StatsPlayer.addCustomStat(plugin, stat, value, boolean addtoexisting).
 * 
 * Where the String plugin parameter is never used to obtain an actual plugin instance.
 * But rather, it's only ever used as a String label to separate stats into different
 * categories (by plugin).
 * 
 * So, essentially, this "plugin" parameter can be anything.
 * So, we'll use it for our stat.getCategory().
 * 
 * @author Nikolai
 */
public class EnjinStats implements Stats {

    @Override
    public void registerStat(Stat stat) {
        // Do nothing
        // Enjin does not have a registration concept
    }

    @Override
    public void registerStatLog(Stat stat) {
        // Do nothing
        // Enjin only supports Integer, Double, & Float Stats
    }

    @Override
    public void setData(Player player, Stat stat, Data value) {
        StatsPlayer enjinStats = new StatsPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        if (stat.getDataType() == DataType.INTEGER) {
            enjinStats.addCustomStat(stat.getCategory(), stat.getName(), value.intValue(), false);
        } else if (stat.getDataType() == DataType.DOUBLE) {
            enjinStats.addCustomStat(stat.getCategory(), stat.getName(), value.doubleValue(), false);
        } else if (stat.getDataType() == DataType.FLOAT) {
            enjinStats.addCustomStat(stat.getCategory(), stat.getName(), value.floatValue(), false);
        } else {
            throw new UnsupportedOperationException("Enjin does not support a Stats with DataType: " + stat.getDataType());
        }
    }

    @Override
    public void increment(Player player, Stat stat) {
        increment(player, stat, 1);
    }

    @Override
    public void increment(Player player, Stat stat, Number amount) {
        StatsPlayer enjinStats = new StatsPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        if (stat.getDataType() == DataType.INTEGER) {
            enjinStats.addCustomStat(stat.getCategory(), stat.getName(), amount.intValue(), true);
        } else if (stat.getDataType() == DataType.DOUBLE) {
            enjinStats.addCustomStat(stat.getCategory(), stat.getName(), amount.doubleValue(), true);
        } else if (stat.getDataType() == DataType.FLOAT) {
            enjinStats.addCustomStat(stat.getCategory(), stat.getName(), amount.floatValue(), true);
        } else {
            throw new UnsupportedOperationException("Enjin does not support Stats with DataType: " + stat.getDataType());
        }
    }

    @Override
    public Data getData(Player player, Stat stat) {
        throw new UnsupportedOperationException("Must get Data from the Enjin website.");
    }

    @Override
    public Map<Stat, Data> getPlayerStats(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Multimap<Integer, PlayerData> getLeaderboard(Stat stat, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

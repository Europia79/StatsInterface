package mc.euro.stats.spi.plugins;


import com.enjin.officialplugin.stats.StatsPlayer;

import java.util.Map;

import mc.euro.stats.api.Data;
import mc.euro.stats.api.DataType;
import mc.euro.stats.api.InvalidDataException;
import mc.euro.stats.api.Stat;
import mc.euro.stats.spi.Stats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Example Enjin API code:
 * StatsPlayer enjinStats = new StatsPlayer(Bukkit.getOfflinePlayer(uuid));
 * enjinStats.addCustomStat("BattleArena", statName, 1, true);
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
        StatsPlayer enjinStats = new StatsPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        enjinStats.
    }

    @Override
    public Map<Stat, Data> getPlayerStats(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<Integer, Map<String, Data>> getTopStats(Stat stat, int top) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

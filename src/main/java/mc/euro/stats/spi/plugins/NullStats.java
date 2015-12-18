package mc.euro.stats.spi.plugins;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.Map;

import mc.euro.stats.api.Stat;
import mc.euro.stats.api.xyz.Data;
import mc.euro.stats.api.xyz.PlayerData;
import mc.euro.stats.api.xyz.StatName;
import mc.euro.stats.spi.Stats;

import org.bukkit.entity.Player;

/**
 * 
 * @author Nikolai
 */
public class NullStats implements Stats {

    @Override
    public void registerStat(Stat stat) {
        // do nothing
    }

    @Override
    public void registerStatLog(Stat stat) {
        // do nothing
    }

    @Override
    public void setData(Player player, Stat stat, Data value) {
        // do nothing
    }

    @Override
    public void increment(Player player, Stat stat) {
        // do nothing
    }

    @Override
    public void increment(Player player, Stat stat, Number amount) {
        // do nothing
    }

    @Override
    public Data getData(Player player, Stat stat) {
        return new Data("0");
    }

    @Override
    public Map<StatName, Data> getPlayerStats(Player player) {
        return new HashMap<StatName, Data>();
    }

    @Override
    public Multimap<Integer, PlayerData> getLeaderboard(StatName stat, int size) {
        return LinkedHashMultimap.create();
    }

}

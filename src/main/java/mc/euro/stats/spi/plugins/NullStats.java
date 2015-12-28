package mc.euro.stats.spi.plugins;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.Map;

import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.PlayerData;
import mc.euro.stats.spi.v0.Stats;

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
    public Map<Stat, Data> getPlayerStats(Player player) {
        return new HashMap<Stat, Data>();
    }

    @Override
    public Multimap<Integer, PlayerData> getLeaderboard(Stat stat, int size) {
        return LinkedHashMultimap.create();
    }

}

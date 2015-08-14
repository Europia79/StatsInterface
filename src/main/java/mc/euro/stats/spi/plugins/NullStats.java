package mc.euro.stats.spi.plugins;

import mc.euro.stats.api.Stat;
import mc.euro.stats.spi.Stats;

import java.util.HashMap;
import java.util.Map;

import mc.euro.stats.api.Data;
import mc.euro.stats.api.InvalidDataException;
import mc.euro.stats.api.DataType;

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
        return new HashMap<>();
    }

    @Override
    public Map<Integer, Map<String, Data>> getTopStats(Stat stat, int top) throws InvalidDataException {
        throw new InvalidDataException("Data not found: No StatEngine is installed.");
    }

}

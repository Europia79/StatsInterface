package mc.euro.stats.spi;

import java.util.Map;

import mc.euro.stats.api.Data;
import mc.euro.stats.api.InvalidDataException;
import mc.euro.stats.api.DataType;
import mc.euro.stats.api.Stat;
import mc.euro.stats.api.xyz.StatData;
import mc.euro.stats.api.xyz.StatMetaData;

import org.bukkit.entity.Player;

/**
 * The main Stats SPI. <br/><br/>
 */
public interface StatsInterface {
    
    // public Collection<Stat> getStats();
    
    /** registering a Stat */
    public void registerStat(Stat stat, DataType type);
    public void registerStatLog(Stat stat); // For example: Logging date & time that an event occured.
    
    /** setting and incrementing player Data for a Stat: */
    public void setData(Player player, Stat stat, Data value); // Any type of data.
    public void increment(Player player, StatMetaData stat); // StatMetaData MUST BE VALIDATED !!!
    public void increment(Player player, StatData data); // Only Number data.
    
    /** getting player Data for a Stat: */
    public Data getData(Player player, StatMetaData stat);
    public Map<Stat, Data> getPlayerStats(Player player); // No InvalidDataException here because we can just return zero for the Data.
    
    /**
     * Getting the Leaderboards:.
     * @param stat Leaderboard for this Stat.
     * @param top For example: top 5 or top 10.
     * @return Maps Position to a Map of Player+Value.
     * @throws InvalidDataException This is how you communicate any problems, like absolutely no results found.
     * Altho, if 10 results are requested, and only 5 are found, then you should return the partial results.
     */
    public Map<Integer, Map<String, Data>> getTopStats(Stat stat, int top) throws InvalidDataException;
    
    /**
     * Redefining a Stat:.
     * Ex: changing the type from INT to DOUBLE
     */
    // public void redefineStat(Stat stat, DataType newType);
    
}

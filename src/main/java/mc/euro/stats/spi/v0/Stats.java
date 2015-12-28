package mc.euro.stats.spi.v0;

import com.google.common.collect.Multimap;

import java.util.Map;

import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.PlayerData;

import org.bukkit.entity.Player;

/**
 * The main Stats SPI. <br/><br/>
 * Should the method parameters generally be
 * interfaces, abstract, concrete, or immutable classes ?
 */
public interface Stats {
    
    // public Collection<Stat> getStats();
    
    /** registering a Stat */
    public void registerStat(Stat stat);
    public void registerStatLog(Stat stat); // For example: Logging date & time that an event occured.
    
    /** setting and incrementing player Data for a Stat: */
    public void setData(Player player, Stat stat, Data value);
    public void increment(Player player, Stat stat);
    public void increment(Player player, Stat stat, Number amount); // Only Number data.
    
    /** getting player Data for a Stat: */
    public Data getData(Player player, Stat stat);
    public Map<Stat, Data> getPlayerStats(Player player); // No InvalidDataException here because we can just return zero for the Data.
    // public Map<String, Map<String, Data>> getStatCategoriesFor(Player player);
    
    /**
     * Getting the Leaderboards:.
     * @param stat Leaderboard for this Stat.
     * @param size For example, top 5 or top 10.
     * @return Maps Position to a Collection of PlayerData, or an empty Map.
     * @example Category = <b>2014-15 NHL Goal Leaderboard</b>
     * <pre>
     * Pos| Player              | Goals
     * ---| --------------------| -----
     *  1 | Alex Ovechkin       | 53
     *  2 | Steven Stamkos      | 43
     *  3 | Rick Nash           | 42
     *  4 | John Tavares        | 38
     *  5 | Max Pacioretty      | 37
     *    | Joe Pavelski        | 37
     *    | Tyler Sequin        | 37
     *    | Vladimir Tarasenko  | 37
     *  9 | Jamie Benn          | 35
     * 10 | Zach Parise         | 33
     *    | Corey Perry         | 33
     * </pre>
     * As you can see, the Multimap can hold duplicate keys.
     * Position 5 has four PlayerData values (because they're tied in goals).
     * The size() is easily accessible.
     * The standard Map view is easily accessible via asMap().
     */
    public Multimap<Integer, PlayerData> getLeaderboard(Stat stat, int size);
    // public Map<Integer, Set<PlayerData>> getTopStats(StatName statName, int top);
    
    /**
     * Redefining a Stat:.
     * Ex: changing the type from INT to DOUBLE
     */
    // public void redefineStat(Stat stat, DataType newType);
    
}

package mc.euro.stats;

import com.google.common.collect.Multimap;

import java.util.Map;
import java.util.Map.Entry;

import mc.alk.arena.executors.CustomCommandExecutor;
import mc.alk.arena.executors.MCCommand;
import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.InvalidDataException;
import mc.euro.stats.api.v0.PlayerData;
import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.api.v0.StatFactory;
import mc.euro.stats.spi.v0.Stats;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Used for testing
 * Also used to play around with use-case scenarios.
 * 
 * @author Nikolai
 */
public class StatExecutor extends CustomCommandExecutor {
    
    Stats engine;

    StatExecutor(Stats engine) {
        this.engine = engine;
    }
    
    /**
     * What would using the API look & feel like ?
     * You only have to create/define a Stat once:
     * then you should simply be able to get it via the Uniqueid: Category + StatName
     * String statId = category + "." + name
     * Stat s = StatFactory.get(statId);
     */
    @MCCommand(cmds = {"register", "registerStat"}, op = true)
    public boolean registerStatCmd(CommandSender sender, String category, String statName, String type, String... context) {
        Stat stat = StatFactory.defineStat( // create/define a Stat
                StatFactory.category(category),
                StatFactory.name(statName),
                StatFactory.type(type),
                StatFactory.context(context));
        engine.registerStat(stat);
        return true;
    }

    @MCCommand(cmds = {"registerEventLog"}, op = true)
    public boolean registerEventLogCmd(CommandSender sender, String category, String statName, String type, String... context) {
        Stat stat = StatFactory.defineStat( // create/define a Stat
                StatFactory.category(category),
                StatFactory.name(statName),
                StatFactory.type(type),
                StatFactory.context(context));
        engine.registerStatLog(stat);
        return true;
    }

    @MCCommand(cmds = {"setData"}, op = true)
    public boolean setDataCmd(Player player, String statId, String value, String... context) {
        Stat stat = StatFactory.get(statId);
        Data data = new Data(value);
        engine.setData(player, stat, data);
        return true;
    }

    @MCCommand(cmds = {"increment", "inc"}, op = true)
    public boolean incrementCmd(Player player, String statId) {
        return incrementCmd(player, statId, 1);
    }

    @MCCommand(cmds = {"increment", "inc"}, op = true)
    public boolean incrementCmd(Player player, String statId, Number amount) {
        Stat stat = StatFactory.get(statId);
        Data data = getData(player, stat);
        System.out.println("Stat value before: " + data.toString());
        engine.increment(player, stat, amount);
        System.out.println("Stat value after: " + data.toString());
        return true;
    }

    @MCCommand(cmds = {"getData"}, op = true)
    public boolean getDataCmd(Player player, String statId) {
        Stat stat = StatFactory.get(statId);
        Data data = getData(player, stat);
        player.sendMessage("value = " + data.toString());
        return true;
    }
    
    public Data getData(Player player, Stat stat) {
        return engine.getData(player, stat);        
    }

    @MCCommand(cmds = {"getPlayerStats"}, op = true)
    public boolean getPlayerStatsCmd(Player player) {
        Map<Stat, Data> datamap = engine.getPlayerStats(player);
        datamap.forEach( (Stat s, Data d) -> player.sendMessage(String.format("%s: %s", s.getUniqueId(), d.toString())) );
        return true;
    }

    @MCCommand(cmds = {"getTopStats", "getLeaderBoard"}, op = true)
    public boolean getTopStatsCmd(Player player, String statId, int top) throws InvalidDataException {
        Stat stat = StatFactory.get(statId);
        Multimap<Integer, PlayerData> pmap = engine.getLeaderboard(stat, top);
        String msg = "%s. - %s";
        for (Entry entry : pmap.entries()) {
            player.sendMessage(String.format(msg, entry.getKey().toString(), entry.getValue().toString()));
        }
        return true;
    }

}

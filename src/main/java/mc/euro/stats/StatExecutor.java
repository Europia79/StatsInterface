package mc.euro.stats;

import java.util.Map;

import mc.alk.arena.executors.CustomCommandExecutor;
import mc.alk.arena.executors.MCCommand;
import mc.euro.stats.api.Data;
import mc.euro.stats.api.InvalidDataException;
import mc.euro.stats.api.DataType;
import mc.euro.stats.api.Stat;
import mc.euro.stats.spi.Stats;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author Nikolai
 */
public class StatExecutor extends CustomCommandExecutor {
    
    Stats engine;

    StatExecutor(Stats engine) {
        this.engine = engine;
    }

    @MCCommand(cmds = {"registerStat"}, op = true)
    public boolean registerStat(CommandSender sender, String category, String statName, String type) {
        Stat stat = new Stat(category, statName);
        DataType dataType = DataType.valueOf(type);
        engine.registerStat(stat, dataType);
        String msg = "" + stat.getUniqueId() + " has been registered.";
        sender.sendMessage(msg);
        return true;
    }

    @MCCommand(cmds = {"registerStatLog"}, op = true)
    public boolean registerStatLog(CommandSender sender, String category, String statName) {
        Stat stat = new Stat(category, statName);
        engine.registerStatLog(stat);
        return true;
    }

    @MCCommand()
    public boolean setData(CommandSender sender, Player player, String category, String statName, String value, String... context) {
        Stat stat = new Stat(category, statName);
        Data data = new Data(value);
        engine.setData(player, stat, data);
        return true;
    }

    @MCCommand()
    public void increment(Player player, Stat stat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @MCCommand()
    public void increment(Player player, Stat stat, Number amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @MCCommand()
    public Data getData(Player player, Stat stat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @MCCommand()
    public Map<Stat, Data> getPlayerStats(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @MCCommand()
    public Map<Integer, Map<String, Data>> getTopStats(Stat stat, int top) throws InvalidDataException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

package mc.euro.stats;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.ServiceLoader;

import mc.alk.arena.executors.CustomCommandExecutor;
import mc.alk.arena.executors.MCCommand;
import mc.euro.stats.api.Stat;
import mc.euro.stats.api.xyz.Data;
import mc.euro.stats.api.xyz.DataType;
import mc.euro.stats.api.xyz.InvalidDataException;
import mc.euro.stats.api.xyz.MetaInfo;
import mc.euro.stats.api.xyz.MetaInfo.ContextItem;
import mc.euro.stats.api.xyz.MetaInfo.InfoBuilder;
import mc.euro.stats.api.xyz.StatInfo;
import mc.euro.stats.api.xyz.StatMetaInfo;
import mc.euro.stats.api.xyz.StatName;
import mc.euro.stats.spi.Stats;

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
     */
    @MCCommand(cmds = {"register"}, op = true)
    public boolean register(CommandSender sender, String category, String statName, String type, String... context) {
        Iterator<Stats> it = ServiceLoader.load(Stats.class).iterator();
        Stat stat = Stat.create( // create/define a Stat
                Stat.category(category),
                Stat.name(statName),
                Stat.type(type),
                Stat.context(context));
        // You only have to create/define a Stat once:
        // then you should simply be able to get it via the UniqueId: Category + StatName
        // Stat s = Stat.getStat(Stat.category(category), Stat.name(statName));
        engine.registerStat(stat);
        return true;
    }

    /**
     * What would using the API look & feel like ?
     */
    @MCCommand(cmds = {"registerStat"}, op = true)
    public boolean registerStat(CommandSender sender, String category, String statName, String type, String... context) {
        DataType dataType = DataType.valueOf(type);
        StatInfo statInfo = new StatInfo(category, statName, dataType);
        InfoBuilder builder = new MetaInfo.InfoBuilder();
        LinkedHashSet<ContextItem> iset = parseContext(context);
        for (ContextItem ci : iset) {
            builder.addContext(ci.name, ci.type);
        }
        MetaInfo metaInfo = builder.create();
        Stat stat = new StatMetaInfo(statInfo, metaInfo);
        engine.registerStat(stat);
        String msg = "" + stat.getUniqueId() + " has been registered.";
        sender.sendMessage(msg);
        return true;
    }
    
    private LinkedHashSet<ContextItem> parseContext(String[] context) {
        LinkedHashSet<ContextItem> result = new LinkedHashSet<ContextItem>();
        for (int x = 0; x < context.length; x = x + 2) {
            for (int y = 1; y < context.length; y = y + 2) {
                String name = context[x];
                String type = context[y];
                result.add(new ContextItem(name, DataType.valueOf(type)));
            }
        }
        return result;
    }

    @MCCommand(cmds = {"registerStatLog"}, op = true)
    public boolean registerStatLog(CommandSender sender, String category, String statName) {
        StatName stat = new StatName(category, statName);
        engine.registerStatLog(null);
        return true;
    }

    @MCCommand()
    public boolean setData(CommandSender sender, Player player, String category, String statName, String value, String... context) {
        Data data = new Data(value);
        StatInfo statInfo = new StatInfo(category, statName, DataType.STRING);
        MetaInfo metaInfo = new MetaInfo.InfoBuilder().create();
        Stat stat = new StatMetaInfo(statInfo, metaInfo);
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

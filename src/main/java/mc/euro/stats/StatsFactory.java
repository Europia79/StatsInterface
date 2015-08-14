package mc.euro.stats;

import mc.euro.stats.spi.plugins.EnjinStats;
import mc.euro.stats.spi.plugins.LolmewnStatsTwo;
import mc.euro.stats.spi.plugins.NullStats;
import mc.euro.stats.spi.plugins.ScoreboardStatsImpl;
import mc.euro.stats.spi.plugins.AlkarinStats;
import mc.euro.stats.spi.Stats;

import com.github.games647.scoreboardstats.ScoreboardStats;

import java.util.ArrayList;
import java.util.List;

import mc.alk.tracker.Tracker;
import mc.alk.tracker.TrackerInterface;
import mc.euro.version.Version;
import mc.euro.version.VersionFactory;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import nl.lolmewn.stats.api.StatsAPI;

/**
 * 
 * @author Nikolai
 */
public class StatsFactory {
    
    Plugin plugin;
    boolean trackStats;
    
    public StatsFactory(Plugin reference) {
        this(reference, true);
    }
    
    public StatsFactory(Plugin reference, boolean trackStats) {
        this.plugin = reference;
        this.trackStats = trackStats;
    }
    
    public Stats getNewInstance() {
        return getNewInstances().get(0);
    }
    
    public List<Stats> getNewInstances() {
        
        List<Stats> results = new ArrayList<Stats>();
        
        Version<Plugin> lolmewn = VersionFactory.getPluginVersion("Stats");
        Version<Plugin> alkarin = VersionFactory.getPluginVersion("BattleTracker");
        Version<Plugin> enjin = VersionFactory.getPluginVersion("EnjinMinecraftPlugin");
        Version<Plugin> sb = VersionFactory.getPluginVersion("ScoreboardStats");
        
//        if (lolmewn.isCompatible("3") && trackStats) {
//            RegisteredServiceProvider<StatsAPI> container = plugin.getServer().getServicesManager()
//                    .getRegistration(nl.lolmewn.stats.api.StatsAPI.class);
//            StatsAPI api = container.getProvider();
//            results.add(new LolmewnStatsThree(api));
//        }
        if (lolmewn.isCompatible("2") && trackStats) {
            RegisteredServiceProvider<StatsAPI> container = plugin.getServer().getServicesManager()
                    .getRegistration(nl.lolmewn.stats.api.StatsAPI.class);
            StatsAPI api = container.getProvider();
            results.add(new LolmewnStatsTwo(api));
        }
        if (alkarin.isEnabled() && trackStats) {
            String database = plugin.getName();
            TrackerInterface ti = Tracker.getInterface(database);
            results.add(new AlkarinStats(ti));
        }
        if (sb.isEnabled()) {
            ScoreboardStats sbplugin = (ScoreboardStats) Bukkit.getPluginManager().getPlugin("ScoreboardStats");
            results.add(new ScoreboardStatsImpl(sbplugin) );
        }
        if (enjin.isCompatible("2.6") && trackStats) {
            results.add(new EnjinStats());
        }
        if (results.isEmpty()) {
            results.add(new NullStats());
        }
        return results;
    }

}

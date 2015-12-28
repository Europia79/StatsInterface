package mc.euro.stats;

import mc.euro.stats.spi.plugins.LolmewnStatsTwo;
import mc.euro.stats.spi.v0.Stats;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * 
 * @author Nikolai
 */
public class StatsPlugin extends JavaPlugin implements CommandExecutor {
    
    Stats stats;
    ServicesManager sm;
    
    @Override
    public void onEnable() {
        this.sm = getServer().getServicesManager();
        registerStatPlugins();
        this.stats = new StatsFactory(this).getStatsInterface();
        getCommand("istats").setExecutor(new StatExecutor(stats));
    }
    
    public Stats getStatsInterface() {
        return this.stats;
    }
    
    private void registerStatPlugins() {
        hookStatsPlugin("Stats", LolmewnStatsTwo.class, ServicePriority.High, "nl.lolmewn.stats.api.Stat");
    }
    
    private void hookStatsPlugin(String name, Class<? extends Stats> hookClass, ServicePriority priority, String...packages) {
        try {
            if (packagesExists(packages)) {
                Stats statPlugin = hookClass.getConstructor().newInstance();
                sm.register(Stats.class, statPlugin, this, priority);
                getLogger().info(String.format("[Stats] %s found", name));
            }
        } catch (Exception e) {
            getLogger().severe(String.format("[Permission] There was an error hooking %s - check to make sure you're using a compatible version!", name));
        }
    }
    
    /**
     * Determines if all packages in a String array are within the Classpath
     * This is the best way to determine if a specific plugin exists and will be
     * loaded. If the plugin package isn't loaded, we shouldn't bother waiting
     * for it!
     * @param packages String Array of package names to check
     * @return Success or Failure
     */
    private static boolean packagesExists(String...packages) {
        try {
            for (String pkg : packages) {
                Class.forName(pkg);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

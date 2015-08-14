package mc.euro.stats;

import mc.euro.stats.spi.Stats;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * 
 * @author Nikolai
 */
public class StatsPlugin extends JavaPlugin implements CommandExecutor {
    
    Stats stats;
    
    @Override
    public void onEnable() {
        this.stats = new StatsFactory(this).getNewInstance();
        getCommand("istats").setExecutor(new StatExecutor(stats));
    }
    
    public Stats getStatsInterface() {
        return this.stats;
    }

}

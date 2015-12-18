package mc.euro.stats.spi.plugins;

import mc.euro.stats.api.Stat;
import mc.euro.stats.spi.Stats;

import java.util.Map;

import mc.alk.tracker.TrackerInterface;
import mc.alk.tracker.objects.WLT;
import mc.euro.stats.api.xyz.Data;
import mc.euro.stats.api.xyz.InvalidDataException;
import mc.euro.stats.api.xyz.DataType;

import org.bukkit.entity.Player;


/**
 * 
 * @author Nikolai
 */
public abstract class AlkarinStats implements Stats {
    
    TrackerInterface api;
    
    public AlkarinStats(TrackerInterface reference) {
        this.api = reference;
    }

}

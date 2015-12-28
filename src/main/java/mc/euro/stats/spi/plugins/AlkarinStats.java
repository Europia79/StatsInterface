package mc.euro.stats.spi.plugins;

import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.spi.v0.Stats;

import java.util.Map;

import mc.alk.tracker.TrackerInterface;
import mc.alk.tracker.objects.WLT;
import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.InvalidDataException;
import mc.euro.stats.api.v0.DataType;

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

package mc.euro.stats.spi.plugins;


import java.util.Map;

import mc.euro.stats.api.xyz.Data;
import mc.euro.stats.api.xyz.DataType;
import mc.euro.stats.api.xyz.InvalidDataException;
import mc.euro.stats.api.Stat;
import mc.euro.stats.spi.Stats;

import org.bukkit.entity.Player;

import nl.lolmewn.stats.api.StatsAPI;

/**
 * 
 * @author Nikolai
 */
public abstract class LolmewnStatsThree implements Stats {
    
    StatsAPI api;
    
    public LolmewnStatsThree(StatsAPI ref) {
        this.api = ref;
    }
}

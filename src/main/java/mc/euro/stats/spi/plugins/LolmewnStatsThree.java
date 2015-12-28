package mc.euro.stats.spi.plugins;


import java.util.Map;

import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.DataType;
import mc.euro.stats.api.v0.InvalidDataException;
import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.spi.v0.Stats;

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

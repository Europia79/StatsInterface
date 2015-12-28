package mc.euro.stats.spi.plugins;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import com.github.games647.scoreboardstats.ScoreboardStats;
import com.github.games647.scoreboardstats.pvpstats.PlayerStats;

import java.sql.Connection;
import java.util.Map;

import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.DataType;
import mc.euro.stats.api.v0.InvalidDataException;
import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.spi.v0.Stats;

import org.bukkit.entity.Player;


/**
 * 
 * @author Nikolai
 */
public abstract class ScoreboardStatsImpl implements Stats {
    
    ScoreboardStats api;
    
    public ScoreboardStatsImpl(ScoreboardStats reference) {
        this.api = reference;
    }

}

package mc.euro.stats.spi.plugins;

import mc.euro.stats.api.Stat;
import mc.euro.stats.spi.Stats;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import com.github.games647.scoreboardstats.ScoreboardStats;
import com.github.games647.scoreboardstats.pvpstats.PlayerStats;

import java.sql.Connection;
import java.util.Map;

import mc.euro.stats.api.Data;
import mc.euro.stats.api.InvalidDataException;
import mc.euro.stats.api.DataType;

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

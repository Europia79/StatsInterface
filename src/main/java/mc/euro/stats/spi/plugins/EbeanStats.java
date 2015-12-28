package mc.euro.stats.spi.plugins;

import mc.euro.stats.api.v0.Stat;
import mc.euro.stats.spi.v0.Stats;

import com.avaje.ebean.EbeanServer;

import java.util.Map;

import mc.euro.stats.api.v0.Data;
import mc.euro.stats.api.v0.InvalidDataException;
import mc.euro.stats.api.v0.DataType;

import org.bukkit.entity.Player;

/**
 * 
 * @author Nikolai
 */
public abstract class EbeanStats implements Stats {
    
    EbeanServer database;
    
    public EbeanStats(EbeanServer server) {
        this.database = server;
    }

}

package mc.euro.stats.spi.plugins;

import mc.euro.stats.api.Stat;
import mc.euro.stats.spi.Stats;

import com.avaje.ebean.EbeanServer;

import java.util.Map;

import mc.euro.stats.api.Data;
import mc.euro.stats.api.InvalidDataException;
import mc.euro.stats.api.DataType;

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

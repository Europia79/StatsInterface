package mc.euro.stats;

import com.google.common.collect.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Nikolai
 */
public interface StatEngine {
    
    public Table<String, String, Number> getTable(String name);
    public Map<String, Table<String, String, Number>> getTables();
    
    public static final StatEngine ENGINE = new StatEngine() {
        
        /**
         * [Row, Column, Value]:.
         * 
         * String Row can either be Player Name or Player UUID.
         */
        Map<String, Table<String, String, Number>> tables = new HashMap<String, Table<String, String, Number>>();

        @Override
        public Table<String, String, Number> getTable(String name) {
            Table<String, String, Number> t = tables.get(name);
            String uuid = "uuid";
            Integer x = (Integer) t.column("kills").get(uuid);
            Table CTF_ARENA_TABLE = t;
            getTables().put("CtfArena", CTF_ARENA_TABLE);
            return tables.get(name);
        }

        // BattleArena.getStatsInterface().
        // BattleArena.getStatEngine().
        // BattleArena.getStatController().
        // BattleArena.getStatManager(). 
        @Override
        public Map<String, Table<String, String, Number>> getTables() {
            String uuid = "uuid";
            //     getCategory("SndArena").getStats("kills").get(uuid).increment();
            //     getCategory("SndArena").getStats("kills").get(uuid).intValue();
            // getStatCategory("SndArena").getStats("kills").get(uuid).intValue();
            int kills = getTable("SndArena").column("kills").get(uuid).intValue();
            getTable("SndArena").put(uuid, "kills", kills + 1);
            return tables;
        }
    };

}

package mc.euro.stats.api.xyz;

/**
 * 
 * @author Nikolai
 */
public class StatInfo extends StatName {
    
    DataType type;
    
    public StatInfo(String category, String statName, DataType type) {
        super(category, statName);
        this.type = type;
    }
    
    public StatInfo(StatName stat, DataType type) {
        super(stat.category, stat.name);
        this.type = type;
    }
    
    public DataType getDataType() {
        return this.type;
    }

}

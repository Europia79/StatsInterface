package mc.euro.stats.api.xyz;

import mc.euro.stats.api.DataType;

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
    
    public StatInfo(StatName sname, DataType type) {
        super(sname.category, sname.statName);
        this.type = type;
    }
    
    public DataType getDataType() {
        return this.type;
    }

}

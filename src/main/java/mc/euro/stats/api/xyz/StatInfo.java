package mc.euro.stats.api.xyz;

import mc.euro.stats.api.DataType;

/**
 * 
 * @author Nikolai
 */
public class StatInfo extends StatName {
    
    DataType type;
    
    public StatInfo(StatName sname, DataType type) {
        super(sname.getCategoryName(), sname.getStatName());
        this.type = type;
    }
    
    public DataType getDataType() {
        return this.type;
    }

}

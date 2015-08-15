package mc.euro.stats.api.xyz;

import java.util.Map;

import mc.euro.stats.api.xyz.MetaInfo.MetaData;

/**
 * 
 * @author Nikolai
 */
public class StatMetaData {
    
    StatMetaInfo stat;
    MetaData metadata;
    
    public StatMetaData(StatMetaInfo stat, MetaData metadata) {
        this.stat = stat;
        this.metadata = metadata;
    }
    
    public StatName getStat() {
        return this.stat;
    }
    
    public MetaData getMetaData() {
        return this.metadata;
    }
    
    public Map<String, Object> getContextualValues() {
        return this.metadata.getMetaData();
    }

}

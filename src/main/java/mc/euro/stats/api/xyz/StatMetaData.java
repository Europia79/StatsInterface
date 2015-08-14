package mc.euro.stats.api.xyz;

import java.util.LinkedHashMap;

/**
 * 
 * @author Nikolai
 */
public class StatMetaData {
    
    StatName stat;
    LinkedHashMap<String, Object> contextualValues;
    
    public StatMetaData(StatName stat, LinkedHashMap<String, Object> contextualValues) {
        this.stat = stat;
        this.contextualValues = contextualValues;
    }
    
    public StatMetaData(StatMetaData meta) {
        this(meta.stat, meta.contextualValues);
    }
    
    public StatName getStat() {
        return this.stat;
    }
    
    public LinkedHashMap<String, Object> getContextualValues() {
        return this.contextualValues;
    }

}

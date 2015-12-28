package mc.euro.stats.api.xyz;

import com.google.common.collect.ImmutableSet;

import mc.euro.stats.api.Stat;
import mc.euro.stats.api.xyz.MetaInfo.Context;

/**
 * 
 * @author Nikolai
 */
public class StatMetaInfo extends StatInfo implements Stat {
    
    MetaInfo metaInfo;
    
    public StatMetaInfo(StatInfo stat, MetaInfo metaInfo) {
        super(stat.category, stat.name, stat.type);
        this.metaInfo = metaInfo;
    }
    
    public MetaInfo getMetaInfo() {
        return this.metaInfo;
    }
    
    /**
     * Context is a Set of Contextual Information.
     */
    public ImmutableSet<Context> getOtherColumns() {
        return metaInfo.getContext();
    }

}

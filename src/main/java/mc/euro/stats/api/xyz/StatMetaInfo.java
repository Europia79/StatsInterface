package mc.euro.stats.api.xyz;

import java.util.Set;

import mc.euro.stats.api.xyz.MetaInfo.MetaItem;

/**
 * 
 * @author Nikolai
 */
public class StatMetaInfo extends StatInfo {
    
    MetaInfo metaInfo;
    
    public StatMetaInfo(StatInfo stat, MetaInfo metaInfo) {
        super(stat.category, stat.statName, stat.type);
        this.metaInfo = metaInfo;
    }
    
    public MetaInfo getMetaInfo() {
        return this.metaInfo;
    }
    
    public Set<MetaItem> getMetaItems() {
        return metaInfo.getMetaInfo();
    }
}

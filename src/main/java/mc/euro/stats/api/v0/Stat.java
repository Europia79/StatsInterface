package mc.euro.stats.api.v0;

import com.google.common.collect.ImmutableSet;

import mc.euro.stats.api.v0.MetaInfo.Context;

/**
 * What is a Stat ?
 * It has a name, category, datatype, and metadata (or contextual information). 
 * metadata example: arenaName or blockMetaData.
 * The combination of StatCategory + StatName makes it unique.
 * 
 * @author Nikolai
 */
public interface Stat {
    
    public String getCategory();
    public String getName();
    public DataType getDataType();
    public ImmutableSet<Context> getOtherColumns();
    
    public default String getUniqueId() {
        return getCategory() + "." + getName();
    }

}

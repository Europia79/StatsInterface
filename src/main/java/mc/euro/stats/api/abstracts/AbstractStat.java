package mc.euro.stats.api.abstracts;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

import mc.euro.stats.api.Category;
import mc.euro.stats.api.StatId;
import mc.euro.stats.api.StatName;
import mc.euro.stats.api.xyz.DataType;
import mc.euro.stats.api.xyz.MetaInfo;

/**
 * 
 * @author Nikolai
 */
public abstract class AbstractStat {
    
    public abstract String getCategory();
    public abstract String getName();
    public abstract DataType getDataType();
    public abstract Set<MetaInfo.Context> getContext();
    
    public abstract StatId getUniqueId();
    public String getUniqueIdString() {
        return getCategory() + "." + getName();
    }
    
    public static AbstractStat create(Category category, StatName name, DataType type, ImmutableSet<MetaInfo.ContextItem> context) {
        return null;
    };
    
    public static Category category(String category) {
        return null;
    }
    
    public static StatName name(String name) {
        return null;
    }
    
    public static DataType type(String type) {
        return null;
    }
    
    public static ImmutableSet<MetaInfo.ContextItem> context() {
        return null;
    }

}

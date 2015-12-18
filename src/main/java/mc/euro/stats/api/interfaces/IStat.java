package mc.euro.stats.api.interfaces;

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
public interface IStat {
    
    public String getCategory();
    public String getName();
    public DataType getDataType();
    public Set<MetaInfo.Context> getContext();
    
    public StatId getUniqueId();
    public default String getUniqueIdString() {
        return getCategory() + "." + getName();
    }
    
    public static IStat create(Category category, StatName name, DataType type, ImmutableSet<MetaInfo.ContextItem> context) {
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

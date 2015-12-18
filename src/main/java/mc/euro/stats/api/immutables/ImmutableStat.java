package mc.euro.stats.api.immutables;

import com.google.common.collect.ImmutableSet;

import mc.euro.stats.api.Category;
import mc.euro.stats.api.StatName;
import mc.euro.stats.api.concretes.ConcreteStat;
import mc.euro.stats.api.xyz.DataType;
import mc.euro.stats.api.xyz.MetaInfo;

/**
 * 
 * @author Nikolai
 */
public final class ImmutableStat {
    
    private final Category category;
    private final StatName name;
    private final DataType type;
    private final ImmutableSet<MetaInfo.ContextItem> context;
    
    public ImmutableStat(Category category, StatName name, DataType type, ImmutableSet<MetaInfo.ContextItem> contextLabels) {
        this.category = category;
        this.name = name;
        this.type = type;
        this.context = contextLabels;
    }
    
    public String getCategory() {
        return this.category.toString();
    }
    
    public String getName() {
        return this.name.getName();
    }
    
    public DataType getDataType() {
        return this.type;
    }
    
    public ImmutableSet<MetaInfo.ContextItem> getContext() {
        return this.context;
    }
    
    public String getUniqueId() {
        return getCategory() + "." + getName();
    }
    
    
    
    
    
    
    /*
     * *******************************************************************8*****
     */
    public static ConcreteStat create(Category category, StatName name, DataType type, ImmutableSet<MetaInfo.ContextItem> context) {
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

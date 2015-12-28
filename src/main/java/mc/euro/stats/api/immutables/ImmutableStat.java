package mc.euro.stats.api.immutables;

import com.google.common.collect.ImmutableSet;

import mc.euro.stats.api.Category;
import mc.euro.stats.api.Stat;
import mc.euro.stats.api.StatName;
import mc.euro.stats.api.xyz.DataType;
import mc.euro.stats.api.xyz.MetaInfo.Context;

/**
 * 
 * @author Nikolai
 */
public final class ImmutableStat implements Stat {
    
    private final Category category;
    private final StatName name;
    private final DataType type;
    private final ImmutableSet<Context> context;
    
    public ImmutableStat(Category category, StatName name, DataType type, ImmutableSet<Context> contextLabels) {
        this.category = category;
        this.name = name;
        this.type = type;
        this.context = contextLabels;
    }
    
    @Override
    public String getCategory() {
        return this.category.toString();
    }
    
    @Override
    public String getName() {
        return this.name.getName();
    }
    
    @Override
    public DataType getDataType() {
        return this.type;
    }
    
    @Override
    public ImmutableSet<Context> getOtherColumns() {
        return this.context;
    }
    
    @Override
    public String getUniqueId() {
        return getCategory() + "." + getName();
    }

}

package mc.euro.stats.api.v0;

import com.google.common.collect.ImmutableSet;

import mc.euro.stats.api.v0.MetaInfo.Context;

/**
 * 
 * @author Nikolai
 */
public final class ImmutableStat implements Stat {
    
    private final Category category;
    private final StatName name;
    private final DataType type;
    private final ImmutableSet<MetaInfo.Context> context;
    
    public ImmutableStat(Category category, StatName name, DataType type, ImmutableSet<MetaInfo.Context> otherColumns) {
        this.category = category;
        this.name = name;
        this.type = type;
        this.context = otherColumns;
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

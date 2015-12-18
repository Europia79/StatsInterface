package mc.euro.stats.api;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.LinkedHashSet;

import mc.euro.stats.api.xyz.DataType;
import mc.euro.stats.api.xyz.MetaInfo.Context;
import mc.euro.stats.api.xyz.MetaInfo.ContextItem;

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
    public ImmutableSet<Context> getContext();
    
    public default String getUniqueId() {
        return getCategory() + "." + getName();
    }
    
    public static Stat create(Category category, StatName name, DataType type, ImmutableSet<Context> context) {
        return new Stat() {

            @Override
            public String getCategory() {
                return category.getCategory();
            }

            @Override
            public String getName() {
                return name.getName();
            }

            @Override
            public DataType getDataType() {
                return type;
            }

            @Override
            public ImmutableSet<Context> getContext() {
                return context;
            }
        };
    };
    
    public static Category category(String category) {
        return new Category() {

            @Override
            public String getCategory() {
                return category;
            }
        };
    }
    
    public static StatName name(String name) {
        return new StatName() {

            @Override
            public String getName() {
                return name;
            }
        };
    }
    
    public static DataType type(String type) {
        return DataType.valueOf(type);
    }
    
    public static ImmutableSet<Context> context(String[] contextPairs) {
        LinkedHashSet<ContextItem> result = new LinkedHashSet<ContextItem>();
        for (int x = 0; x < contextPairs.length; x = x + 2) {
            for (int y = 1; y < contextPairs.length; y = y + 2) {
                String name = contextPairs[x];
                String type = contextPairs[y];
                result.add(new ContextItem(name, DataType.valueOf(type)));
            }
        }
        return ImmutableSet.<Context>copyOf(new Context.Builder(result).build());
    }

}

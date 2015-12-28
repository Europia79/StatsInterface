package mc.euro.stats.api.v0;

import com.google.common.collect.ImmutableSet;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 
 * @author Nikolai
 */
public class StatFactory {
    
    static Map<String, Stat> stats = new HashMap<>();
    
    public static Stat get(String uniqueId) {
        return stats.get(uniqueId);
    }
    
    public static Stat createStat(Category category, StatName name, DataType type, ImmutableSet<MetaInfo.Context> context) {
        return defineStat(category, name, type, context);
    }
    
    public static Stat defineStat(Category category, StatName name, DataType type, ImmutableSet<MetaInfo.Context> context) {
        return new ImmutableStat(category, name, type, context);
    }
    
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
    
    public static ImmutableSet<MetaInfo.Context> context(String[] contextPairs) {
        LinkedHashSet<MetaInfo.ContextItem> result = new LinkedHashSet<MetaInfo.ContextItem>();
        for (int x = 0; x < contextPairs.length; x = x + 2) {
            for (int y = 1; y < contextPairs.length; y = y + 2) {
                String name = contextPairs[x];
                String type = contextPairs[y];
                result.add(new MetaInfo.ContextItem(name, DataType.valueOf(type)));
            }
        }
        return ImmutableSet.<MetaInfo.Context>copyOf(new MetaInfo.Context.Builder(result).build());
    }

}

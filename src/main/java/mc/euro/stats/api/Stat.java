package mc.euro.stats.api;

import java.util.Set;

import mc.euro.stats.api.Context.ContextItem;

/**
 * What is a Stat ?
 * It has a name, category, datatype, and metadata. 
 * metadata example: arenaName or blockMetaData.
 * The combination of StatCategory + StatName makes it unique.
 * 
 * @author Nikolai
 */
public class Stat<E> {
    
    final String category;
    final String name;
    final DataType<E> type;
    final Context context;
    
    public Stat(String category, String name, DataType<E> type) {
        this(category, name, type, new Context.Builder().create());
    }
    
    public Stat(String category, String name, DataType<E> type, Context context) {
        this.category = category;
        this.name = name;
        this.type = type;
        this.context = context;
    }
    
    /**
     * @return A concatenation of category + name delimited by a period.
     */
    public String getUniqueId() {
        return category + "." + name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getName() {
        return name;
    }
    
    public DataType getDataType() {
        return this.type;
    }
    
    public Set<ContextItem> getContext() {
        return this.context.getContext();
    }

}

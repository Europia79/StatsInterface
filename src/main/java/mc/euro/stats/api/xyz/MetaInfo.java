package mc.euro.stats.api.xyz;

import com.google.common.collect.ImmutableSet;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * MetaInfo (aka Context) is just a Set of MetaItems.
 * Each MetaItem is a piece of contextual information.
 * Each MetaItem is a key to a corresponding piece of MetaData.
 *
 * @author Nikolai
 */
public class MetaInfo {

    ImmutableSet<Context> contextualInformation;

    private MetaInfo(ImmutableSet<Context> contextualInformation) {
        this.contextualInformation = contextualInformation;
    }
    
    public ImmutableSet<Context> getContext() {
        return this.contextualInformation;
    }

    public static class InfoBuilder {

        int pos = 0;
        LinkedHashSet<Context> cset = new LinkedHashSet<Context>();
        
        public InfoBuilder addContext(String name, DataType type) {
            return addContext(name, type, "0");
        }

        public InfoBuilder addContext(String name, DataType type, String defaultValue) {
            cset.add(new Context(pos, name, type, defaultValue));
            pos = pos + 1;
            return this;
        }
        
        public InfoBuilder addAll(LinkedHashSet<ContextItem> iset) {
            for (ContextItem item : iset) {
                addContext(item.name, item.type);
            }
            return this;
        }

        public MetaInfo create() {
            return new MetaInfo(ImmutableSet.copyOf(cset));
        }
    }
    
    public static class ContextItem {
        
        public final String name;
        public final DataType type;
        
        public ContextItem(String name, DataType type) {
            this.name = name;
            this.type = type;
        }
        
    }

    public static class Context implements Comparable<Context> {

        Integer position;
        String name;
        DataType type;
        String defaultValue;
        
        private Context() { }

        private Context(Integer position, String name, DataType type, String defaultValue) {
            this.position = position;
            this.name = name;
            this.type = type;
            this.defaultValue = defaultValue;
        }

        @Override
        public int compareTo(Context item) {
            return position.compareTo(item.getPosition());
        }

        private Integer getPosition() {
            return this.position;
        }

        public String getName() {
            return this.name;
        }

        public DataType getDataType() {
            return this.type;
        }
        
        public String getDefault() {
            return this.defaultValue;
        }
        
        public static final class Builder {
            
            LinkedHashSet<Context> context = new LinkedHashSet<Context>();
            int position = 0;
            
            public Builder(LinkedHashSet<ContextItem> items) {
                addAll(items);
            }
            
            public Builder addAll(LinkedHashSet<ContextItem> items) {
                for (ContextItem ci : items) {
                    context.add(new Context(position, ci.name, ci.type, ""));
                    position = position + 1;
                }
                return this;
            }
            
            public ImmutableSet<Context> build() {
                return ImmutableSet.<Context>copyOf(context);
            }
        }

    }
    
    public class MetaData {
        
        LinkedHashMap<String, Object> dataMap;
        
        public MetaData() {
            this(new LinkedHashMap<String, Object>());
        }
        
        private MetaData(LinkedHashMap<String, Object> metadataMap) {
            this.dataMap = metadataMap;
        }
        
        public Map<String, Object> getMetaData() {
            return this.dataMap;
        }
    }
    
    public class DataBuilder {
        
        LinkedHashMap<String, Object> dmap;
        
        
        public DataBuilder addData(String key, Object value) {
            dmap.put(key, value);
            return this;
        }
        
        public MetaData create() {
            return new MetaData(dmap);
        }
        
    }

}

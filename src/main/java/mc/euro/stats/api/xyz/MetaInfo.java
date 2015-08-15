package mc.euro.stats.api.xyz;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import mc.euro.stats.api.DataType;

/**
 * MetaInfo (aka Context) is just a Set of MetaItems.
 * Each MetaItem is a piece of contextual information.
 * Each MetaItem is a key to a corresponding piece of MetaData.
 *
 * @author Nikolai
 */
public class MetaInfo {

    Set<MetaItem> contextualInformation;

    private MetaInfo(Set<MetaItem> contextualInformation) {
        this.contextualInformation = contextualInformation;
    }
    
    public Set<MetaItem> getMetaInfo() {
        return this.contextualInformation;
    }

    public static class InfoBuilder {

        int pos = 0;
        Set<MetaItem> cset = new LinkedHashSet<MetaItem>();

        public InfoBuilder addMetaItem(String name, DataType type) {
            cset.add(new MetaItem(pos, name, type));
            pos = pos + 1;
            return this;
        }

        public MetaInfo create() {
            return new MetaInfo(cset);
        }
    }

    public static class MetaItem implements Comparable<MetaItem> {

        Integer position;
        String name;
        DataType type;
        
        private MetaItem() { }

        private MetaItem(Integer position, String name, DataType type) {
            this.position = position;
            this.name = name;
            this.type = type;
        }

        @Override
        public int compareTo(MetaItem item) {
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

    }
    
    public class MetaData {
        
        LinkedHashMap<String, Object> dataMap;
        
        public MetaData() {
            this(new LinkedHashMap<>());
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

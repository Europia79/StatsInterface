package mc.euro.stats.api.xyz;

import java.util.LinkedHashSet;
import java.util.Set;

import mc.euro.stats.api.DataType;

/**
 * MetaInfo (aka Context) is just a Set of contextual information 
 * where each piece in the Set has corresponding MetaData.
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

    public static class Builder {

        int pos = 0;
        Set<MetaItem> cset = new LinkedHashSet<MetaItem>();

        public Builder addMetaItem(String name, DataType type) {
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
        
        
    }

}

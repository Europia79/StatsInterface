package mc.euro.stats.api;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Context is just a Set of contextual information;
 *
 * @author Nikolai
 */
public class Context {

    Set<ContextItem> context;

    private Context(Set<ContextItem> contextualInformation) {
        this.context = contextualInformation;
    }
    
    public Set<ContextItem> getContext() {
        return this.context;
    }

    public static class Builder {

        int pos = 0;
        Set<ContextItem> cset = new LinkedHashSet<ContextItem>();

        public Builder addContextItem(String name, DataType type) {
            cset.add(new ContextItem(pos, name, type));
            pos = pos + 1;
            return this;
        }

        public Context create() {
            return new Context(cset);
        }
    }

    public static class ContextItem implements Comparable<ContextItem> {

        Integer position;
        String name;
        DataType type;
        
        private ContextItem() { }

        private ContextItem(Integer position, String name, DataType type) {
            this.position = position;
            this.name = name;
            this.type = type;
        }

        @Override
        public int compareTo(ContextItem item) {
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
    
    public class ContextData {
        
        
    }

}

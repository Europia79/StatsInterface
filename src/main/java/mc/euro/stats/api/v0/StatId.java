package mc.euro.stats.api.v0;

/**
 * 
 * @author Nikolai
 */
public final class StatId {
    
    private final String category;
    private final String name;
    
    private StatId() {
        this.category = "";
        this.name = "";
    }
    
    public StatId(String category, String name) {
        this.category = category;
        this.name = name;
    }
    
    public String getCategory() {
        return this.category;
    }
    public String getName() {
        return this.name;
    }
    
    public String getUniqueId() {
        return category + "." + name;
    }

}

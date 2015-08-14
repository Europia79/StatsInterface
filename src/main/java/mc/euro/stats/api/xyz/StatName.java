package mc.euro.stats.api.xyz;

/**
 * 
 * @author Nikolai
 */
public class StatName extends StatCategory {
    
    String statName;
    
    public StatName(String category, String name) {
        super(category);
        this.statName = name;
    }
    
    public String getCategoryName() {
        return category;
    }
    
    public String getStatName() {
        return this.statName;
    }
    public String getCanonicalName() {
        return category + "." + statName;
    }

}

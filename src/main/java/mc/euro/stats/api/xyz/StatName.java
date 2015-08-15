package mc.euro.stats.api.xyz;

/**
 * 
 * @author Nikolai
 */
public class StatName extends CategoryName {
    
    String statName;
    
    public StatName(String category, String name) {
        super(category);
        this.statName = name;
    }
    
    public String getStatName() {
        return this.statName;
    }
    /**
     * Alias for getUniqueId() & getFullyQualifiedName()
     * @return String concatenation of categoryName.statName
     */
    public String getCanonicalName() {
        return category + "." + statName;
    }
    
    /**
     * Alias for getUniqueId() & getCanonicalName()
     * @return String concatenation of categoryName.statName
     */
    public String getFullyQualifiedName() {
        return category + "." + statName;
    }
    
    /**
     * Alias for getCanonicalName() & getFullyQualifiedName()
     * @return String concatenation of categoryName.statName
     */
    public String getUniqueId() {
        return category + "." + statName;
    }
    
    /**
     * Alias for getCanonicalName(), getFullyQualifiedName(), & getUniqueId().
     * @return String concatenation of categoryName.statName
     */
    @Override
    public String toString() {
        return category + "." + statName;
    }

}

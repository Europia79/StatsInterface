package mc.euro.stats.api.xyz;

/**
 * 
 * @author Nikolai
 */
public class StatName extends Category {
    
    String name;
    
    public StatName(String category, String name) {
        super(category);
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    /**
     * Alias for getUniqueId() & getFullyQualifiedName()
     * @return String concatenation of categoryName.statName
     */
    public String getCanonicalName() {
        return category + "." + name;
    }
    
    /**
     * Alias for getUniqueId() & getCanonicalName()
     * @return String concatenation of categoryName.statName
     */
    public String getFullyQualifiedName() {
        return category + "." + name;
    }
    
    /**
     * Alias for getCanonicalName() & getFullyQualifiedName()
     * @return String concatenation of categoryName.statName
     */
    public String getUniqueId() {
        return category + "." + name;
    }
    
    /**
     * Alias for getCanonicalName(), getFullyQualifiedName(), & getUniqueId().
     * @return String concatenation of categoryName.statName
     */
    @Override
    public String toString() {
        return category + "." + name;
    }

}

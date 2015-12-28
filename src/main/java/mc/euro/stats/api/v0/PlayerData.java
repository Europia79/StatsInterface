package mc.euro.stats.api.v0;

/**
 * 
 * @author Nikolai
 */
public class PlayerData {
    
    final String playerName;
    final Data data;
    
    public PlayerData(String playerName, Data value) {
        this.playerName = playerName;
        this.data = value;
    }
    
    public String getPlayerName() {
        return this.playerName;
    }
    
    public Data getData() {
        return this.data;
    }

}

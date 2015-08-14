package mc.euro.stats.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Nikolai
 */
public class Data<E> {
    
    private E data;
    private Map<String, Object> contextualValues;
    static final DateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    static final DateFormat DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static final DateFormat TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmss");
    
    private Data() { }
    
    public Data(E data) {
        this(data, new HashMap<String, Object>());
    }
    
    public Data(E data, Map<String, Object> contextualValues) {
        this.data = data;
        this.contextualValues = contextualValues;
    }
    
    /**
     * @return Maps the Context.name to the Context.value
     */
    public Map<String, Object> getContextualValues() {
        return this.contextualValues;
    }

    public E getValue() {
        return data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                ", contextualValues=" + contextualValues +
                '}';
    }
}

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
public class Data {
    
    private String data;
    private Map<String, Object> contextualValues;
    static final DateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    static final DateFormat DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static final DateFormat TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmss");
    
    private Data() { }
    
    public Data(String data) {
        this(data, new HashMap<String, Object>());
    }
    
    public Data(String data, Map<String, Object> contextualValues) {
        this.data = data;
        this.contextualValues = contextualValues;
    }
    
    /**
     * @return Maps the Context.name to the Context.value
     */
    public Map<String, Object> getContextualValues() {
        return this.contextualValues;
    }

    @Override
    public String toString() {
        return this.data;
    }

    public byte byteValue() {
        return Byte.valueOf(data);
    }

    public double doubleValue() {
        return Double.valueOf(data);
    }

    public float floatValue() {
        return Float.valueOf(data);
    }

    public int intValue() {
        return Integer.valueOf(data);
    }

    public long longValue() {
        return Long.valueOf(data);
    }

    public short shortValue() {
        return Short.valueOf(data);
    }
    
    public boolean booleanValue() {
        return Boolean.valueOf(data);
    }
    
    public Date dateValue() throws ParseException {
        return DATE.parse(data);
    }
    
    public Date datetimeValue() throws ParseException {
        return DATETIME.parse(data);
    }
    
    public Date timestampValue() throws ParseException {
        return TIMESTAMP.parse(data);
    }

}

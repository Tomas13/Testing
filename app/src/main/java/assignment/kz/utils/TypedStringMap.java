package assignment.kz.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A String-to-String map with helper methods to put/get values of various types.
 */
public class TypedStringMap extends HashMap<String, String> implements Serializable {

    private static final long serialVersionUID = -5492746583407346451L;

    public TypedStringMap() {
    }

    public TypedStringMap(Map<String, String> map) {
        init(map);
    }

    /**
     * Initialize the typed map from a regular map.
     *
     * @param map - the source map.
     */
    public void init(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            putValue(key, map.get(key));
        }
    }

    public String getString(String key) {
        return get(key);
    }

    public Integer getInt(String key) {
        String value = get(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public TypedStringMap putValue(String key, String value) {
        put(key, value);
        return this;
    }

    public TypedStringMap putValue(String key, Integer value) {
        put(key, value.toString());
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");

        for (String key : keySet()) {
            result.append(key)
                    .append(": ")
                    .append(get(key))
                    .append(", ");
        }

        result = new StringBuilder(result.substring(0, result.length() - 2) + "}");

        return result.toString();
    }
}

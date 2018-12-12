package malmalimet.kz.utils;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Set;

/**
 * Miscellaneous utility functions.
 */
public class MiscUtils {

    /**
     * Convert a bundle containing string values to a map.
     *
     * @param bundle - the bundle to convert.
     * @return - A string-to-string mapping.
     */
    public static HashMap<String, String> stringBundleToHashMap(Bundle bundle) {
        final Set<String> keySet = bundle.keySet();
        final HashMap<String, String> result = new HashMap<>();
        for (final String key : keySet) {
            final Object value = bundle.get(key);
            if (value != null && value instanceof String) {
                result.put(key, (String) value);
            }
        }
        return result;
    }
}

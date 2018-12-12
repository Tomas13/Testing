package malmalimet.kz.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import malmalimet.kz.R;


/**
 * Utility methods for processing strings.
 */
public class StringUtils {

    private static final String ENCODING = "UTF-8";

    /**
     * Return true if the given string exists and is not empty.
     *
     * @param string - the string to test.
     * @return True if the given string is not null and is not empty.
     */
    public static boolean isDefined(String string) {
        return string != null && !string.isEmpty();
    }

    /**
     * Return a new string where the first letter is uppercase.
     *
     * @param string - input string.
     * @return - capitalized string.
     */
    public static String capitalize(String string) {
        if (string != null && string.length() >= 1) {
            if (string.length() == 1) {
                return string.toUpperCase();
            } else {
                return string.substring(0, 1).toUpperCase() + string.substring(1);
            }
        }
        return string;
    }

    /**
     * Encode the given string in the x-www-form-urlencoded format using the UTF-8 encoding scheme.
     *
     * @param text - The text to encode.
     * @return Encoded text.
     */
    public static String encodeUriComponent(String text) {
        if (text != null) {
            String result = null;
            try {
                result = URLEncoder.encode(text, ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * Generate a random universally unique identifier.
     *
     * @return The UUID.
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * If `substring` is indeed a substring of `original`, return a SpannableStringBuilder where the substring
     * is highlighted, otherwise return the original string.
     *
     * @param context   - a context needed to retrieve style resources.
     * @param original  - original string.
     * @param substring - substring to highlight.
     * @return A highlighted string or the original itself.
     */
    public static CharSequence highlightSubstring(Context context, String original, String substring) {
        SpannableStringBuilder builder = new SpannableStringBuilder(original);

        if (substring != null && !substring.isEmpty()) {
            BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(
                    ContextCompat.getColor(context, R.color.highlightColor));
            int startIndex = original.toLowerCase().indexOf(substring);
            if (startIndex >= 0) {
                builder.setSpan(backgroundColorSpan, startIndex, startIndex + substring.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }

        return builder;
    }

    public static String toBase64(byte[] source) {
        return Base64.encodeToString(source, Base64.NO_WRAP);
    }

    public static String unescapeQuotes(String escaped) {
        return escaped.replace("\\\"", "\"")
                .replaceAll("(^\")|(\"$)", "");
    }

    public static String removeWhitespaces(String text) {
        return text.replaceAll("\\s", "");
    }

    public static String multiply(String source, int times) {
        StringBuilder builder = new StringBuilder(source);
        for (int i = 1; i < times; i++) {
            builder.append(source);
        }
        return builder.toString();
    }

    public static String removeExtraSpaces(String text) {
        if (text != null) {
            return text.replaceAll("\\s+", " ");
        }

        return text;
    }
}

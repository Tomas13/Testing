package malmalimet.kz.utils;

import android.text.Html;

import com.google.android.gms.common.util.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Utility methods for formatting stuff into strings to be presented to the user,
 * or sent to the server in some cases.
 */
public class FormattingUtils {

    private static final SimpleDateFormat NUMERIC_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private static final SimpleDateFormat TIME_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private static final SimpleDateFormat SHORT_TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss", Locale.getDefault());
    private static final SimpleDateFormat FULL_DATE_SHORT_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault());
    private static final SimpleDateFormat WORKING_HOURS_DATE_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final SimpleDateFormat WEEKDAY_DATE_FORMAT = new SimpleDateFormat("EEEE", Locale.getDefault());
    private static final SimpleDateFormat MONTH_DATE_FORMAT = new SimpleDateFormat("MMM dd", Locale.getDefault());

    private static final BigDecimal TEN_THOUSANDS = new BigDecimal(10000);

//    private static final String __FRACTION_POUNDS = StringUtils.multiply("#", SenimConstants.AMOUNT_PRECISION);
//    private static final String __FRACTION_ZEROS = StringUtils.multiply("0", SenimConstants.AMOUNT_PRECISION);

//    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###." + __FRACTION_POUNDS);
    private static final DecimalFormat DECIMAL_FORMAT_NO_FRACTION = new DecimalFormat("#,###");
//    private static final DecimalFormat DECIMAL_FORMAT_NO_GROUPING = new DecimalFormat("#." + __FRACTION_POUNDS);
    private static final DecimalFormat DECIMAL_FORMAT_NO_GROUPING_NO_FRACTION = new DecimalFormat("#");
//    private static final DecimalFormat DECIMAL_FORMAT_KEEP_ALL_ZEROS = new DecimalFormat("0,000." + __FRACTION_ZEROS);
//    private static final DecimalFormat DECIMAL_FORMAT_KEEP_ALL_ZEROS_NO_GROUPING = new DecimalFormat("0." + __FRACTION_ZEROS);

    private static final DecimalFormat DECIMAL_FORMAT_KILOMETERS = new DecimalFormat("#.#");

    /**
     * Return a string containing only digit characters from the input.
     *
     * @param s - the input sequence.
     * @return A string with only digits from the input, can be an empty string if there are
     * no digits in the input, or null, if the input itself is null.
     */
    public static String extractDigits(CharSequence s) {
        if (s != null) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < s.length(); ++i) {

                char c = s.charAt(i);
                if (c >= '0' && c <= '9') {
                    result.append(s.charAt(i));
                }
            }
            return result.toString();
        }
        return null;
    }

//    public static String formatCurrency(BigDecimal amount, boolean asFraction) {
////        return formatAmount(amount, asFraction) + " " + SymbolConstants.KZT;
//    }

    /**
     * Format the given number represented in a string.
     *
     * @param number     - the number to format.
     * @param asFraction - if true, the result will be formatted as a fraction.
     * @return Formatted string.
     */
    public static String formatAmount(String number, boolean asFraction) {
        try {
            BigDecimal bigDecimal = new BigDecimal(number);
            return formatAmount(bigDecimal, asFraction, false);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String formatAmount(BigDecimal number, boolean asFraction) {
        return formatAmount(number, asFraction, false);
    }

    /**
     * Format the given BigDecimal.
     *
     * @param number     - the number to format.
     * @param asFraction - if true, the result will be formatted as a fraction.
     * @return Formatted string.
     */
    public static String formatAmount(BigDecimal number, boolean asFraction, boolean keepTrailingZeros) {
        if (number != null) {
            try {
                if (number.abs().compareTo(TEN_THOUSANDS) < 0) {
                    if (keepTrailingZeros) {
//                        return DECIMAL_FORMAT_KEEP_ALL_ZEROS_NO_GROUPING.format(number);
                    } else if (asFraction) {
//                        return DECIMAL_FORMAT_NO_GROUPING.format(number);
                    } else {
                        return DECIMAL_FORMAT_NO_GROUPING_NO_FRACTION.format(number);
                    }
                } else {
                    if (keepTrailingZeros) {
//                        return DECIMAL_FORMAT_KEEP_ALL_ZEROS.format(number);
                    } else if (asFraction) {
//                        return DECIMAL_FORMAT.format(number);
                    } else {
                        return DECIMAL_FORMAT_NO_FRACTION.format(number);
                    }
                }

            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Convert a BigDecimal to String to be sent to server.
     *
     * @param bigDecimal - the BigDecimal to convert.
     * @return The converted string.
     */
//    public static String bigDecimalToFractionString(BigDecimal bigDecimal) {
////        return bigDecimal.setScale(SenimConstants.AMOUNT_PRECISION, RoundingMode.HALF_UP).toPlainString();
//    }

    /**
     * Convert a BigDecimal to String.
     *
     * @param bigDecimal The BigDecimal to convert.
     * @return The converted string.
     */
//    public static String bigDecimalToString(BigDecimal bigDecimal) {
//        if (NumberUtils.isNumberInteger(bigDecimal)) {
//            return bigDecimal.setScale(0, RoundingMode.HALF_UP).toPlainString();
//        } else {
//            return bigDecimalToFractionString(bigDecimal);
//        }
//    }

    /**
     * Format the given phone number string into a human friendly format.
     *
     * @param phoneNumber - The phone number, must exclude the leading plus sign.
     * @return The formatted phone number. Example: "77014567890" -> "+7 (701) 456 7890".
     */
    public static String formatPhoneNumber(String phoneNumber) {

        phoneNumber = extractDigits(phoneNumber);

        if (ValidationUtils.isValidPhoneNumber(phoneNumber)) {
            char countryCode = phoneNumber.charAt(0);
            String regionCode = phoneNumber.substring(1, 4);
            String firstGroup = phoneNumber.substring(4, 7);
            String secondGroup = phoneNumber.substring(7);
            return String.format(Locale.getDefault(), "+%c (%s) %s %s",
                    countryCode, regionCode, firstGroup, secondGroup);
        } else {
            return phoneNumber;
        }
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    /**
     * Format a date so that it includes both the date and time.
     *
     * @param date - the date to format.
     * @return Formatted string.
     */
    public static String formatDateFull(Date date) {
        return FULL_DATE_FORMAT.format(date);
    }

    /**
     * Format a date so that it includes both the date and time
     * Time part is in HH:mm format.
     *
     * @param date - the date to format.
     * @return Formatted string.
     */
    public static String formatDateFullShortTime(Date date) {
        return FULL_DATE_SHORT_TIME_FORMAT.format(date);
    }

    /**
     * Format a date so that only the date part is included. The date part will be represented using numbers.
     *
     * @param date - the date to format.
     * @return Formatted string.
     */
    public static String formatDateAsNumericString(Date date) {
        return NUMERIC_DATE_FORMAT.format(date);
    }

    /**
     * Format a date so that only the time part is included.
     *
     * @param date - the date to format.
     * @return Formatted string.
     */
    public static String formatDateAsTimeString(Date date) {
        return TIME_DATE_FORMAT.format(date);
    }

    public static String formatDateAsShortTimeString(Date date) {
        return SHORT_TIME_FORMAT.format(date);
    }

    /**
     * Format the given date to be displayed in a company's working hours time table.
     *
     * @param date - the date to format.
     * @return Formatted string.
     */
    public static String formatDateAsWorkingHoursString(Date date) {
        return WORKING_HOURS_DATE_FORMAT.format(date);
    }

    public static String formatDateWithThresholds(Date date) {
        final Date now = new Date();
        long dayDifference = DateUtils.getDayDifference(date, now);

        if (dayDifference < 1) {
            return formatDateAsShortTimeString(date);
        } else if (dayDifference < 7) {
            return WEEKDAY_DATE_FORMAT.format(date);
        } else {
            return MONTH_DATE_FORMAT.format(date);
        }
    }

    /**
     * Return a string where each word contains only lowercase letters except for the first letter
     * of each word.
     *
     * @param input The input string.
     * @return Formatted string.
     */
    public static String makeCapitalizedWords(String input) {

        if (input == null) {
            return null;
        }
        if (input.isEmpty() || input.trim().isEmpty()) {
            return input.trim();
        }

        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String lowercase = word.toLowerCase();

            result.append(Character.toUpperCase(lowercase.charAt(0)))
                    .append(lowercase.substring(1));
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static String formatCardNumber(String input) {
        if (input == null) {
            return null;
        }
        if (input.isEmpty() || input.trim().isEmpty()) {
            return input.trim();
        }

        StringBuilder result = new StringBuilder();
        char dot = '\u2022';

        for (int i = 0; i < input.length(); i++) {
            if (i % 4 == 0 && i != 0) {
                result.append("  ");
            }
            if (i < 12) {
                result.append(dot);
            } else {
                result.append(input.charAt(i));
            }
        }

        return result.toString();
    }

    public static CharSequence formatHtml(String html) {
        return Html.fromHtml(html);
    }

    public static CharSequence formatDistance(float distance) {
        if (distance < 1000) {
            return DECIMAL_FORMAT_NO_GROUPING_NO_FRACTION.format(distance) + " м";
        } else {
            float distanceInKm = distance / 1000;
            return DECIMAL_FORMAT_KILOMETERS.format(distanceInKm) + " км";
        }
    }

    public static String formatSecondsAsTimeString(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }
}

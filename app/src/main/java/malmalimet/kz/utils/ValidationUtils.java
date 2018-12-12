package malmalimet.kz.utils;

import android.util.Patterns;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import timber.log.Timber;

/**
 * Functions for handling form validation logic.
 */
public class ValidationUtils {

    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[A-Za-z0-9.,:;?!'\"`^&=|~*+%\\-<>@\\[\\]{}()/\\\\_$#]{6,}$");

    public static final int PHONE_LENGTH = 11;
    public static final int BIN_LENGTH = 12;

    private static final Pattern BIN_PATTERN = Pattern.compile("^\\d{12}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$");
    private static final Pattern IBAN_PATTERN = Pattern.compile("^[kK][zZ][0-9]{2}[0-9]{3}[a-zA-Z0-9]{13}$");
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$");
    private static final Pattern MOBILE_PHONE_NUMBER_PATTERN = Pattern.compile("^77(0[0-9]|47|5[01]|6[0-4]|7[15-8])[0-9]{7}$");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern INSTAGRAM_USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._]+$");
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("^[a-zA-Z0-9_\\-]{1,15}$");
    // 'Ə' is not duplicated here, two versions have different Unicode values.
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-ZА-ЯЁƏӘІҢҒҮҰҚӨҺ][A-Za-zА-Яа-яЁёƏəӘәІіҢңҒғҮүҰұҚқӨөҺһ'][A-Za-zА-Яа-яЁёƏəӘәІіҢңҒғҮүҰұҚқӨөҺһ\\-.' ]*$");

    private static final Pattern VEHICLE_NUMBER_PATTERN = Pattern.compile("^[a-zA-ZА-Яа-яЁё0-9][a-zA-ZА-Яа-яЁё0-9 ]*$");

    /**
     * Return true if the given string is a valid BIN.
     *
     * @param bin - the string to test.
     * @return True if the given string is a valid BIN.
     */
    public static boolean isValidBin(String bin) {
        if (bin == null) {
            return false;
        }

        if (bin.length() != BIN_LENGTH) {
            return false;
        }

        if (!BIN_PATTERN.matcher(bin).matches()) {
            return false;
        }

//        int month = Integer.parseInt(bin.substring(2, 4));
//        if (month < 1 || month > 12) {
//            return false;
//        }
//
//        char type = bin.charAt(4);
//        if (type != '4' && type != '5' && type != '6') {
//            return false;
//        }
//
//        char additional = bin.charAt(5);
//        if (additional != '0' && additional != '1' && additional != '2' && additional != '3') {
//            return false;
//        }

        return isValidControl(bin);
    }

    /**
     * Return true if the given string is a valid IIN.
     *
     * @param iin - the string to test.
     * @return True if the given string is a valid IIN.
     */
    public static boolean isValidIin(String iin) {
        if (iin == null) {
            return false;
        }

        if (iin.length() != BIN_LENGTH) {
            return false;
        }

        if (!BIN_PATTERN.matcher(iin).matches()) {
            return false;
        }

//        int month = Integer.parseInt(iin.substring(2, 4));
//        if (month < 1 || month > 12) {
//            return false;
//        }
//
//        int day = Integer.parseInt(iin.substring(4, 6));
//        if (day < 1 || day > 31) {
//            return false;
//        }
//
//        char century = iin.charAt(6);
//        if (century != '1' && century != '2' && century != '3' && century != '4' && century != '5' && century != '6') {
//            return false;
//        }

        return isValidControl(iin);
    }

    /**
     * Return true if the given string conforms to the IIN rules. Used to distinguish between
     * BIN and IIN.
     *
     * @param iin The string to test.
     * @return True if the given string is an IIN string.
     */
    public static boolean isIin(String iin) {
        if (iin == null) {
            return false;
        }

        if (iin.length() != 12) {
            return false;
        }

        if (!BIN_PATTERN.matcher(iin).matches()) {
            return false;
        }

        int month = Integer.parseInt(iin.substring(2, 4));
        if (month < 1 || month > 12) {
            return false;
        }

        int day = Integer.parseInt(iin.substring(4, 6));
        if (day < 1 || day > 31) {
            return false;
        }

        return isValidControl(iin);
    }

    /**
     * Determine whether the given string is a valid user's first, last or patronymic name.
     *
     * @param text - the string to test.
     * @return True if the given string is a valid name.
     */
    public static boolean isValidName(String text) {
        return text != null && NAME_PATTERN.matcher(text).matches();
    }

    /**
     * Determine whether the given string represents a valid email.
     *
     * @param text - the string to test.
     * @return True if the given text is a valid email.
     */
    public static boolean isValidEmail(String text) {
        return text != null && EMAIL_PATTERN.matcher(text).matches();
    }

    /**
     * Determine whether the given string represents a valid IBAN.
     *
     * @param text - the string to test.
     * @return True if the given string is a valid IBAN.
     */
    public static boolean isValidIban(String text) {
        boolean validPattern = text != null && IBAN_PATTERN.matcher(text).matches();
        return validPattern && isValidIbanControlSum(text.toUpperCase());
    }

    private static boolean isValidIbanControlSum(String iban) {
        if (iban.length() < 5 || iban.length() > 34) {
            return false;
        }

        String s = iban.substring(4) + iban.substring(0, 4);
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            int k;
            int c = s.charAt(i);
            if (48 <= c && c <= 57) {
                if (i == s.length() - 4 || i == s.length() - 3) {
                    Timber.d("В позициях 1 и 2 не могут быть цифры");
                    return false;
                }
                k = c - 48;
            } else if (65 <= c && c <= 90) {
                if (i == s.length() - 2 || i == s.length() - 1) {
                    Timber.d("В позициях 3 и 4 не могут быть буквы");
                    return false;
                }
                k = c - 55;
            } else {
                Timber.d("Разрешены только прописные буквы от A до Z и цифры");
                return false;
            }
            if (k > 9)
                r = (100 * r + k) % 97;
            else
                r = (10 * r + k) % 97;
        }
        if (r != 1) {
            Timber.d("Контрольная сумма ошибочна");
            return false;
        }
        return true;
    }

    /**
     * Determine whether the given string represents a valid user identifier.
     *
     * @param text - the string to test.
     * @return True if the given text is a valid identifier.
     */
    public static boolean isValidIdentifier(String text) {
        return text != null && IDENTIFIER_PATTERN.matcher(text).matches();
    }

    public static boolean isValidMobilePhoneNumber(String text) {
        return text != null && MOBILE_PHONE_NUMBER_PATTERN.matcher(text).matches();
    }

    /**
     * Determine whether the given string represents a valid phoneNumber number.
     *
     * @param text The string to test.
     * @return True if the given text is a valid phone number.
     */
    public static boolean isValidPhoneNumber(String text) {
        return text != null && PHONE_NUMBER_PATTERN.matcher(text).matches();
    }

    /**
     * Determine whether the given string is a valid price.
     *
     * @param text The string to test.
     * @return True if the given text is a valid price.
     */
    public static boolean isValidPrice(String text) {
        if (text != null) {
            double value;
            try {
                value = Double.parseDouble(text);
            } catch (NumberFormatException e) {
                value = 0;
            }
            return isValidPrice(value);
        } else {
            return false;
        }
    }

    /**
     * Determine whether the given string represents a number.
     *
     * @param number - the string to test.
     * @return True if the given string is a valid number.
     */
    public static boolean isValidNumber(String number) {
        return number != null && NUMBER_PATTERN.matcher(number).matches();
    }

    /**
     * Determine whether the given string is a valid integer.
     *
     * @param number - the string to test.
     * @return True if the given string is a valid integer.
     */
    public static boolean isValidInteger(String number) {
        return number != null && INTEGER_PATTERN.matcher(number).matches();
    }

    /**
     * Determine whether the given string is a valid password.
     *
     * @param text - the string to test.
     * @return True if the given string is a valid password.
     */
    public static boolean isValidPassword(String text) {
        boolean basic = text != null && text.length() >= PASSWORD_MIN_LENGTH && text.length() <= PASSWORD_MAX_LENGTH;
        return basic && PASSWORD_PATTERN.matcher(text).matches();
    }

    public static boolean isValidUrl(String text) {
        try {
            if (!text.startsWith("http")) {
                text = "http://" + text;
            }
            new URL(text);
            return Patterns.WEB_URL.matcher(text).matches();
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public static boolean isValidFacebookUrl(String text) {
        if (isValidUrl(text)) {
            try {
                if (!text.startsWith("http")) {
                    text = "http://" + text;
                }

                final URL url = new URL(text);
                final String hostName = url.getHost();
                return hostName.endsWith("facebook.com") && url.getPath().length() > 1;
            } catch (MalformedURLException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean isValidInstagram(String text) {
        if (isValidUrl(text)) {
            try {
                if (!text.startsWith("http")) {
                    text = "http://" + text;
                }

                final URL url = new URL(text);
                final String hostName = url.getHost();
                return hostName.endsWith("instagram.com") &&
                        url.getPath().length() > 1 &&
                        isValidInstagram(url.getPath().substring(1));
            } catch (MalformedURLException e) {
                return false;
            }
        } else {
            return INSTAGRAM_USERNAME_PATTERN.matcher(text).matches();
        }
    }

    /**
     * Determine whether the given string is a valid vehicle number.
     *
     * @param text - the string to test.
     * @return True if the given string is a valid vehicle number.
     */
    public static boolean isValidVehicleNumber(String text) {
        return text != null && VEHICLE_NUMBER_PATTERN.matcher(text).matches();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Check if the given IIN/BIN string contains a valid control digit. It is assumed
     * that the string otherwise conforms to the IIN/BIN standard.
     *
     * @param iinOrBin - the string to test.
     * @return True if the given string contains a valid control digit.
     */
    private static boolean isValidControl(String iinOrBin) {
        int control = 0;
        for (int i = 0; i < iinOrBin.length() - 1; i++) {
            control += parseDigit(iinOrBin.charAt(i)) * (i + 1);
        }

        if (control % 11 == 10) {
            control = 0;
            for (int i = 0; i < iinOrBin.length() - 1; i++) {
                if (i < 9) {
                    control += parseDigit(iinOrBin.charAt(i)) * (i + 3);
                } else {
                    control += parseDigit(iinOrBin.charAt(i)) * (i - 8);
                }
            }
        }

        return parseDigit(iinOrBin.charAt(11)) == control % 11;
    }

    /**
     * Convert the given character into its corresponding integer, if possible.
     * For example, character '5' is converted into integer 5.
     *
     * @param c - the character to convert.
     * @return Parsed integer.
     */
    private static int parseDigit(char c) {
        return Character.getNumericValue(c);
    }

    /**
     * Determine whether the given amount is a valid price.
     *
     * @param price - the price.
     * @return True if the given price is valid.
     */
    private static boolean isValidPrice(double price) {
        // [1,  18 446 744 073 709 551 615 ]
        return price >= 1;
    }
}

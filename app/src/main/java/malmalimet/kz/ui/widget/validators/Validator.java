package malmalimet.kz.ui.widget.validators;

import android.support.annotation.StringRes;

import malmalimet.kz.R;
import malmalimet.kz.utils.StringUtils;
import malmalimet.kz.utils.ValidationUtils;
import timber.log.Timber;

public abstract class Validator<T> {

    // The only method.
    public abstract Result validate(T value);

    static final int VALIDATOR_NONE = 1;
    public static final int VALIDATOR_REQUIRED = 2;
    static final int VALIDATOR_PHONE_NUMBER = 4;
    static final int VALIDATOR_EMAIL = 8;
    static final int VALIDATOR_NUMBER = 16;
    static final int VALIDATOR_PASSWORD = 32;
    static final int VALIDATOR_IIN = 64;
    static final int VALIDATOR_IBAN = 128;
    static final int VALIDATOR_IIN_OR_BIN = 256;
    static final int VALIDATOR_NAME = 512;
    static final int VALIDATOR_WEB_URL = 1024;
    static final int VALIDATOR_FACEBOOK_URL = 2048;
    static final int VALIDATOR_INSTAGRAM = 4096;
    static final int VALIDATOR_PERCENT = 8192;
    static final int VALIDATOR_MOBILE_PHONE = 16384;

    public static class Result {
        public boolean isValid;

        @StringRes
        public int errorMessageId;

        public String errorMessage;

        public Result(boolean valid, @StringRes int errorMessageId) {
            this.isValid = valid;
            this.errorMessageId = errorMessageId;
        }

        public Result(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }

        public Result() {
            this.isValid = true;
            this.errorMessage = null;
        }
    }

    public static Validator combineValidators(Validator... validators) {
        return new Validator() {
            @Override
            public Result validate(Object value) {
                Result result;
                for (int i = validators.length - 1; i >= 0; i--) {
                    //noinspection unchecked
                    result = validators[i].validate(value);
                    if (!result.isValid) {
                        return result;
                    }
                }

                return new Result(true, 0);
            }
        };
    }

    public static class BinValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidBin(value), R.string.error_invalid_bin);
        }
    }

    public static class EmailValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidEmail(value), R.string.error_invalid_email);
        }
    }

    public static class IbanValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidIban(value), R.string.error_invalid_iban);
        }
    }

    public static class IinValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidIin(value), R.string.error_invalid_iin);
        }
    }

    public static class IinOrBinValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null
                    || value.isEmpty()
                    || ValidationUtils.isValidIin(value)
                    || ValidationUtils.isValidBin(value),
                    R.string.error_invalid_iin_or_bin);
        }
    }

    public static class NameValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null
                    || value.isEmpty()
                    || ValidationUtils.isValidName(value),
                    R.string.error_invalid_name);
        }
    }

    public static class NumberValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(ValidationUtils.isValidNumber(value), R.string.error_invalid_number);
        }
    }

    public static class PasswordValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidPassword(value), R.string.error_invalid_password);
        }
    }

    public static class PhoneNumberValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            Timber.d(value);
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidPhoneNumber(value), R.string.error_invalid_phone);
        }
    }

    public static class RequiredValidator extends Validator {
        @Override
        public Result validate(Object value) {
            if (value instanceof String) {
                return new Result(StringUtils.isDefined((String) value), R.string.error_field_required);
            } else {
                return new Result(value != null, R.string.error_field_required);
            }
        }
    }

    public static class RequiredStringValidator extends Validator<String> {

        @Override
        public Result validate(String value) {
            return new Result(StringUtils.isDefined(value), R.string.error_field_required);
        }
    }

    public static class WebUrlValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidUrl(value), R.string.error_invalid_format);
        }
    }

    public static class FacebookUrlValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidFacebookUrl(value), R.string.error_invalid_format);
        }
    }

    public static class InstagramValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            return new Result(value == null || value.isEmpty() || ValidationUtils.isValidInstagram(value), R.string.error_invalid_format);
        }
    }

    public static class PercentValidator extends Validator<String> {
        @Override
        public Result validate(String value) {
            if (value == null || value.isEmpty()) {
                return new Result(true, null);
            } else {
                try {
                    int intValue = Integer.parseInt(value);
                    if (intValue < 0) {
                        return new Result(false, R.string.error_less_than_zero);
                    } else if (intValue > 100) {
                        return new Result(false, R.string.error_greater_than_hundred);
                    } else {
                        return new Result(true, null);
                    }
                } catch (NumberFormatException e) {
                    return new Result(false, R.string.error_invalid_format);
                }
            }
        }
    }

    public static class MobilePhoneValidator extends Validator<String> {

        @Override
        public Result validate(String value) {
            return new Result(
                    value == null || ValidationUtils.isValidMobilePhoneNumber(value),
                    R.string.error_invalid_mobile_phone);
        }
    }
}

package malmalimet.kz.ui.widget.validators;

import java.util.ArrayList;
import java.util.List;

import malmalimet.kz.ui.widget.FormField;


public class ValidatorSet {

    private List<Validator> mValidators;
    private FormField mFormField;

    private boolean mChanged = false;
    private boolean mNeverValidated = true;
    private int mType;

    private boolean mListenersInitialized = false;

    public ValidatorSet(FormField formField, int flags) {

        mFormField = formField;

        mValidators = new ArrayList<>();

        if ((flags & Validator.VALIDATOR_NONE) != 0) {
            return;
        }

        if ((flags & Validator.VALIDATOR_REQUIRED) != 0)
            mValidators.add(new Validator.RequiredValidator());
        if ((flags & Validator.VALIDATOR_PHONE_NUMBER) != 0)
            mValidators.add(new Validator.PhoneNumberValidator());
        if ((flags & Validator.VALIDATOR_EMAIL) != 0)
            mValidators.add(new Validator.EmailValidator());
        if ((flags & Validator.VALIDATOR_NUMBER) != 0)
            mValidators.add(new Validator.NumberValidator());
        if ((flags & Validator.VALIDATOR_PASSWORD) != 0)
            mValidators.add(new Validator.PasswordValidator());
        if ((flags & Validator.VALIDATOR_IIN) != 0)
            mValidators.add(new Validator.IinValidator());
        if ((flags & Validator.VALIDATOR_IBAN) != 0)
            mValidators.add(new Validator.IbanValidator());
        if ((flags & Validator.VALIDATOR_IIN_OR_BIN) != 0)
            mValidators.add(new Validator.IinOrBinValidator());
        if ((flags & Validator.VALIDATOR_NAME) != 0)
            mValidators.add(new Validator.NameValidator());
        if ((flags & Validator.VALIDATOR_WEB_URL) != 0)
            mValidators.add(new Validator.WebUrlValidator());
        if ((flags & Validator.VALIDATOR_FACEBOOK_URL) != 0)
            mValidators.add(new Validator.FacebookUrlValidator());
        if ((flags & Validator.VALIDATOR_INSTAGRAM) != 0)
            mValidators.add(new Validator.InstagramValidator());
        if ((flags & Validator.VALIDATOR_PERCENT) != 0)
            mValidators.add(new Validator.PercentValidator());
        if ((flags & Validator.VALIDATOR_MOBILE_PHONE) != 0)
            mValidators.add(new Validator.MobilePhoneValidator());

        mType = flags;
        enable();
    }

    public void addValidator(Validator validator) {
        mValidators.add(validator);
        if (!mListenersInitialized) {
            enable();
        }
    }

    public void enable() {
        if (mValidators.size() > 0) {
            mListenersInitialized = true;
            mFormField.addOnFocusChangedListener(this::onFocusChange);
            mFormField.addOnValueChangedListener(this::onValueChanged);
        }
    }

    public void disable() {
        mListenersInitialized = false;
        mFormField.removeOnFocusChangedListener(this::onFocusChange);
        mFormField.removeOnValueChangedListener(this::onValueChanged);
    }

    public void reset() {
        mNeverValidated = true;
        mFormField.clearError();
    }

    public boolean wasValidated() {
        return !mNeverValidated;
    }

    public boolean checkIfValid() {
        Object value = mFormField.getValue();
        Validator.Result result;

        for (Validator validator : mValidators) {
            //noinspection unchecked
            result = validator.validate(value);
            if (!result.isValid) {
                return false;
            }
        }

        return true;
    }

    public boolean validate() {

        mNeverValidated = false;

        Object value = mFormField.getValue();
        Validator.Result result;

        for (Validator validator : mValidators) {
            //noinspection unchecked
            result = validator.validate(value);
            if (!result.isValid) {
                String errorMessage;
                if (result.errorMessageId != 0) {
                    errorMessage = mFormField.getContext().getString(result.errorMessageId);
                } else {
                    errorMessage = result.errorMessage;
                }
                mFormField.setError(errorMessage);
                return false;
            }
        }

        mFormField.clearError();
        return true;
    }

    private void onFocusChange(boolean hasFocus) {
        if (!hasFocus && mChanged) {
            validate();
        }
    }

    private void onValueChanged(Object value) {
        mChanged = true;
        if (!mNeverValidated) {
            validate();
        }
    }
}

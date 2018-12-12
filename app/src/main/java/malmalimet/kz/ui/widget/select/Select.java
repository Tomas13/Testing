package malmalimet.kz.ui.widget.select;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import java.util.LinkedList;
import java.util.List;

import kotlin.Unit;
import malmalimet.kz.R;
import malmalimet.kz.databinding.WidgetSelectBinding;
import malmalimet.kz.ui.widget.FormField;
import malmalimet.kz.ui.widget.base.BaseView;
import malmalimet.kz.ui.widget.validators.Validator;
import malmalimet.kz.ui.widget.validators.ValidatorSet;
import malmalimet.kz.utils.ImageUtils;
import malmalimet.kz.utils.UiUtils;
import rx.functions.Action1;

public class Select extends BaseView implements FormField {

    private WidgetSelectBinding mBinding;
    private String mLabel;
    private boolean mNullable;

    private List<SelectOption> mOptions;
    private SelectOption mSelectedOption;

    private ValidatorSet mValidatorSet;
    private List<Action1> mOnSelectListeners = new LinkedList<>();

    private int mCaretErrorMargin;
    private int mCaretDefaultMargin;

    public Select(@NonNull Context context) {
        this(context, null);
    }

    public Select(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        mBinding = DataBindingUtil.inflate(inflater, R.layout.widget_select, this, true);

        int validatorType;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Select, 0, 0);
        try {
            mLabel = a.getString(R.styleable.Select_select_label);
            mNullable = a.getBoolean(R.styleable.Select_select_nullable, false);
            validatorType = a.getInt(R.styleable.Select_select_validator, 0);
        } finally {
            a.recycle();
        }

        if (mLabel != null) {
            if ((validatorType & Validator.VALIDATOR_REQUIRED) != 0) {
                mLabel += " *";
            }
            mBinding.textInputLayout.setHint(mLabel);
        }

        mBinding.textInputLayout.setFocusable(false);
        mBinding.textInputLayout.setErrorEnabled(true);
        mBinding.textInputLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));

        mBinding.editText.setTextColor(context.getResources().getColor(R.color.colorBackground));
        mBinding.editText.setFocusable(false);
        mBinding.editText.setLongClickable(false);
        mBinding.editText.setCursorVisible(false);

        mBinding.editText.setOnClickListener(v -> showMenu());

        mValidatorSet = new ValidatorSet(this, validatorType);

        mCaretErrorMargin = (int) UiUtils.convertDpToPx(context, 24);
        mCaretDefaultMargin = (int) UiUtils.convertDpToPx(context, 8);
    }

    public void setOptions(List<SelectOption> options) {
        mOptions = options;
        // If there's only one item, set it as selected.
        if (!mNullable) {
            if (options != null && options.size() == 1) {
                setSelectedOption(options.get(0));
                onSelect(options.get(0));
            }
        } else {
            mSelectedOption = null;
            mBinding.editText.setText(null);
        }
    }

    public void setSelectedOption(SelectOption option) {
        if (mSelectedOption != option) {
            mSelectedOption = option;

            if (mSelectedOption != null) {
                setOptionTitle(mSelectedOption);
            } else {
                mBinding.editText.setText(null);
            }
        }
    }

    public void setOnSelectListener(Action1<SelectOption> listener) {
        mOnSelectListeners.add(listener);
        if (mSelectedOption != null) {
            listener.call(mSelectedOption);
        }
    }

    public void setDisabled(boolean disabled) {
        mBinding.textInputLayout.setEnabled(!disabled);
        mBinding.editText.setEnabled(!disabled);
        if (disabled) {
            ImageUtils.setTint(mBinding.caret, R.color.gray);
        } else {
            ImageUtils.setTint(mBinding.caret, R.color.black);
        }
    }

    public void addValidator(Validator validator) {
        mValidatorSet.addValidator(validator);
    }

    private void showMenu() {
        SelectDialog dialog = new SelectDialog()
                .setTitle(mLabel)
                .setItems(mOptions)
                .setSelectedItem(mSelectedOption)
                .setNullable(mNullable)
                .setOnSelect(selectOption -> {

                    mSelectedOption = selectOption;

                    if (selectOption == null) {
                        mBinding.editText.setText(null);
                    } else {
                        setOptionTitle(selectOption);
                    }

                    onSelect(selectOption);

                    return Unit.INSTANCE;
                });

        showDialog(dialog);
    }

    @SuppressWarnings("unchecked")
    private void onSelect(SelectOption selectOption) {
        for (Action1 listener : mOnSelectListeners) {
            listener.call(selectOption);
        }
    }

    private void setOptionTitle(SelectOption selectOption) {
        if (selectOption.getTitleRes() != 0) {
            mBinding.editText.setText(selectOption.getTitleRes());
        } else {
            mBinding.editText.setText(selectOption.getTitle());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // FormField implementation.

    @Override
    public boolean validate() {
        return mValidatorSet.validate();
    }

    @Override
    public boolean checkIfValid() {
        return mValidatorSet.checkIfValid();
    }

    @Override
    public void resetValidators() {
        mValidatorSet.reset();
    }

    @Override
    public void setError(String error) {
//        Drawable errorIcon = UiUtils.getTextFieldErrorDrawable(getContext());
//        if (errorIcon != null) {
//            mBinding.editText.setError(error, errorIcon);
//        } else {
//            mBinding.editText.setError(error);
//        }

        mBinding.textInputLayout.setError(error);

//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mBinding.caret.getLayoutParams();
//        layoutParams.rightMargin = mCaretErrorMargin;
    }

    @Override
    public void clearError() {
//        mBinding.editText.setError(null);
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mBinding.caret.getLayoutParams();
//        layoutParams.rightMargin = mCaretDefaultMargin;
        mBinding.textInputLayout.setError(null);
    }

    @Override
    public Object getValue() {
        return mSelectedOption;
    }

    @Override
    public void addOnValueChangedListener(Action1<Object> listener) {
        mOnSelectListeners.add(listener);
    }

    @Override
    public void removeOnValueChangedListener(Action1<Object> listener) {
        mOnSelectListeners.remove(listener);
    }

    @Override
    public void addOnFocusChangedListener(Action1<Boolean> listener) {
    }

    @Override
    public void removeOnFocusChangedListener(Action1<Boolean> listener) {
    }

    @Override
    public boolean wasValidated() {
        return mValidatorSet.wasValidated();
    }
}

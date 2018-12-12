package malmalimet.kz.ui.widget.select;

import android.databinding.BindingAdapter;

import java.util.List;

import malmalimet.kz.ui.widget.validators.Validator;
import rx.functions.Action1;

public class SelectBindings {

    @BindingAdapter("select_options")
    public static void setOptions(Select select, List<SelectOption> options) {
        select.setOptions(options);
    }

    @BindingAdapter("select_selected")
    public static void setSelectedOption(Select select, SelectOption selectedOption) {
        select.setSelectedOption(selectedOption);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("select_onSelect")
    public static void setOnSelectListener(Select select, Action1 listener) {
        select.setOnSelectListener(listener);
    }

    @BindingAdapter("select_disabled")
    public static void setDisabled(Select select, boolean disabled) {
        select.setDisabled(disabled);
    }

    @BindingAdapter("select_customValidator")
    public static void setCustomValidator(Select select, Validator validator) {
        select.addValidator(validator);
    }
}

package malmalimet.kz.utils

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.widget.Spinner
import malmalimet.kz.utils.SpinnerExtensions.getSpinnerValue
import malmalimet.kz.utils.SpinnerExtensions.setSpinnerInverseBindingListener
import malmalimet.kz.utils.SpinnerExtensions.setSpinnerValue

class InverseSpinnerBindings {

    @BindingAdapter("selectedValue")
    fun Spinner.setSelectedValue(selectedValue: Any?) {
        setSpinnerValue(selectedValue)
    }

    @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSpinnerInverseBindingListener(inverseBindingListener)
    }

    companion object InverseSpinnerBindings {

        @JvmStatic
        @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
        fun Spinner.getSelectedValue(): Any? {
            return getSpinnerValue()
        }
    }
}
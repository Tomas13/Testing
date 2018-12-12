package malmalimet.kz.utils

import android.databinding.BindingAdapter
import android.widget.Spinner
import malmalimet.kz.utils.SpinnerExtensions.setSpinnerEntries
import malmalimet.kz.utils.SpinnerExtensions.setSpinnerItemSelectedListener
import malmalimet.kz.utils.SpinnerExtensions.setSpinnerValue

class SpinnerBindings {

    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: List<Any>?) {
        setSpinnerEntries(entries)
    }

    @BindingAdapter("onItemSelected")
    fun Spinner.setItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @BindingAdapter("newValue")
    fun Spinner.setNewValue(newValue: Any?) {
        setSpinnerValue(newValue)
    }
}
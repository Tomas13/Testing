package kz.senim.ui.dialog.type

import android.widget.DatePicker
import java.util.*

/**
 * A dialog for picking a date. Default Android date picker is used.
 */
class DatePickerDialog : BaseDialog(), Dialog, android.app.DatePickerDialog.OnDateSetListener {

    private var date: Date? = null
    private var onSubmit: (Date) -> Unit = {}

    private lateinit var dialog: android.app.DatePickerDialog

    fun setDate(date: Date?): DatePickerDialog {
        this.date = date
        return this
    }

    fun onSubmit(callback: (Date) -> Unit): DatePickerDialog {
        this.onSubmit = callback
        return this
    }

    override fun show() {
        val cal = Calendar.getInstance()
        cal.time = date ?: Date()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

        dialog = android.app.DatePickerDialog(context, this, year, month, dayOfMonth)

        dialog.show()

        finalizeShow(dialog)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()
        cal.time = date ?: Date()

        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        onSubmit(cal.time)
    }
}

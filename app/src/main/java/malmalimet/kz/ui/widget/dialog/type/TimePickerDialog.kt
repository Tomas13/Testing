package kz.senim.ui.dialog.type

import android.text.format.DateFormat
import android.widget.TimePicker
import java.util.*

class TimePickerDialog : BaseDialog(), Dialog, android.app.TimePickerDialog.OnTimeSetListener {

    private var date: Date? = null
    private var callback: ((Date) -> Unit)? = null

    private lateinit var dialog: android.app.TimePickerDialog

    fun setDate(date: Date?): TimePickerDialog {
        this.date = date
        return this
    }

    fun setCallback(onTimeSet: (Date) -> Unit): TimePickerDialog {
        this.callback = onTimeSet
        return this
    }

    override fun show() {

        val cal = Calendar.getInstance()
        cal.time = date

        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        dialog = android.app.TimePickerDialog(context, this, hour, minute, DateFormat.is24HourFormat(context))
        dialog.show()
        finalizeShow(dialog)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        if (callback != null) {
            val cal = Calendar.getInstance()

            cal.time = date

            cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
            cal.set(Calendar.MINUTE, minute)
            callback?.invoke(cal.time)
        }
    }
}

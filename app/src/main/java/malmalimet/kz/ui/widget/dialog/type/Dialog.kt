package kz.senim.ui.dialog.type

import android.content.Context


interface Dialog {
    fun setContext(context: Context): Dialog
    fun setTitle(title: String?): Dialog
    fun setTypeId(id: String): Dialog
    fun show()
    fun dismiss()
}

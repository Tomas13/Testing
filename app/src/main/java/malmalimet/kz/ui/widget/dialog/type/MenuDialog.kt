package kz.senim.ui.dialog.type

import android.app.AlertDialog
import android.support.annotation.StringRes
import malmalimet.kz.R
import malmalimet.kz.ui.widget.select.SelectOption

import rx.functions.Action1

/**
 * A dialog which lets the user select one of the predefined menu items.
 * The distinction between a MenuDialog and a [SelectDialog] is that
 * [SelectDialog] has a currently selected item, whereas MenuDialog does not.
 */
open class MenuDialog : BaseDialog(), Dialog {

    private var items: List<SelectOption>? = null
    private var onSelectCallback: ((SelectOption) -> Unit)? = null

    private var cancelButtonEnabled: Boolean = false
    private var cancelButtonStringRes: Int = R.string.action_cancel

    private lateinit var dialog: AlertDialog

    override fun setTitle(title: String?): MenuDialog {
        super.setTitle(title)
        return this
    }

    fun setItems(items: List<SelectOption>): MenuDialog {
        this.items = items
        return this
    }

    fun onSelect(onSelect: (SelectOption) -> Unit): MenuDialog {
        onSelectCallback = onSelect
        return this
    }

    fun withCancelButton(): MenuDialog {
        return withCancelButton(R.string.action_cancel)
    }

    fun withCancelButton(@StringRes stringRes: Int): MenuDialog {
        cancelButtonEnabled = true
        cancelButtonStringRes = stringRes
        return this
    }

    override fun show() {

        if (items?.isNotEmpty() != true) {
            throw RuntimeException("Items not set for MenuDialog")
        }

        val items = arrayOfNulls<CharSequence>(items!!.size)
        for (i in this.items!!.indices) {
            val option = this.items!![i]
            if (option.titleRes != 0) {
                items[i] = context.getString(option.titleRes)
            } else {
                items[i] = option.title
            }
        }

        val builder = makeBuilder()
        if (title != null) {
            builder.setTitle(title)
        } else if (titleRes != 0) {
            builder.setTitle(context.getString(titleRes))
        }

        if (cancelButtonEnabled) {
            builder.setCancelable(true)
                    .setNegativeButton(cancelButtonStringRes, null)
        }

        dialog = builder
                .setItems(items) { _, which ->
                    onSelectCallback?.invoke(this.items!![which])
                }
                .show()

        finalizeShow(dialog)
    }
}

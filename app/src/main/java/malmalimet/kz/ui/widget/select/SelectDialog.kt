package malmalimet.kz.ui.widget.select

import android.app.AlertDialog
import kz.senim.ui.dialog.type.BaseDialog
import kz.senim.ui.dialog.type.Dialog
import malmalimet.kz.R


/**
 * A dialog which lets the user to select one of the provided options.
 * Displays a radio icon next to items, with a selected icon next to the selected item.
 */
class SelectDialog : BaseDialog(), Dialog {

    private var items: List<SelectOption> = listOf()
    private var selectedItem: SelectOption? = null
    private var onSelect: ((SelectOption?) -> Unit)? = null
    private var nullable = false

    private lateinit var dialog: AlertDialog

    override fun setTitle(title: String?): SelectDialog {
        super.setTitle(title)
        return this
    }

    fun setItems(items: List<SelectOption>): SelectDialog {
        this.items = items
        return this
    }

    fun setSelectedItem(item: SelectOption?): SelectDialog {
        selectedItem = item
        return this
    }

    fun setOnSelect(onSelect: (SelectOption?) -> Unit): SelectDialog {
        this.onSelect = onSelect
        return this
    }

    fun setNullable(nullable: Boolean): SelectDialog {
        this.nullable = nullable
        return this
    }

    override fun show() {
        if (items.isEmpty()) {
            return
        }

        val builder = makeBuilder()
        val offset = if (nullable) 1 else 0

        val itemNames = arrayOfNulls<CharSequence>(items.size + offset)
        for (i in items.indices) {
            val option = items[i]
            if (option.titleRes != 0) {
                itemNames[i + offset] = context.getString(option.titleRes)
            } else {
                itemNames[i + offset] = option.title
            }
        }

        if (nullable) {
            itemNames[0] = context.getString(R.string.label_select_empty)
        }

        var checkedItem = -1
        if (nullable && selectedItem == null) {
            checkedItem = 0
        } else if (selectedItem != null) {
            for (i in offset until items.size + offset) {
                if (items[i - offset].optionId == selectedItem!!.optionId) {
                    checkedItem = i
                }
            }
        }

        builder.setTitle(title)
        builder.setSingleChoiceItems(itemNames, checkedItem) { _, which ->
            if (onSelect != null) {
                if (nullable && which == 0) {
                    onSelect?.invoke(null)
                } else {
                    onSelect?.invoke(items[which - offset])
                }
            }

            dismiss()
        }

        dialog = builder.show()

        finalizeShow(dialog)
    }
}

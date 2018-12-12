package kz.senim.ui.dialog.type

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.support.annotation.StringRes

import malmalimet.kz.R

/**
 * A basic confirmation dialog, showing a text message and allowing the user
 * to either confirm or reject an action.
 */
class ConfirmDialog : BaseDialog(), Dialog {

    private var message: CharSequence? = null

    private var onConfirm: () -> Unit = {}
    private var onReject: () -> Unit = {}

    @StringRes private var positiveButtonStringRes = R.string.action_ok
    @StringRes private var negativeButtonStringRes = R.string.action_cancel

    private var positiveButtonText: String? = null
    private var negativeButtonText: String? = null
    private var extraButtonText: String? = null
    private var onExtraButtonClick: () -> Unit = {}

    private lateinit var dialog: AlertDialog

    private var confirmed = false
    private var rejected = false

    override fun setTitle(title: String?): ConfirmDialog {
        super.setTitle(title)
        return this
    }

    fun setMessage(message: CharSequence): ConfirmDialog {
        this.message = message
        return this
    }

    fun onConfirm(onConfirm: () -> Unit): ConfirmDialog {
        this.onConfirm = onConfirm
        return this
    }

    fun onReject(onReject: () -> Unit): ConfirmDialog {
        this.onReject = onReject
        return this
    }

    fun setPositiveButtonText(@StringRes stringRes: Int): ConfirmDialog {
        positiveButtonStringRes = stringRes
        return this
    }

    fun setPositiveButtonText(text: String): ConfirmDialog {
        positiveButtonText = text
        return this
    }

    fun setNegativeButtonText(text: String): ConfirmDialog {
        negativeButtonText = text
        return this
    }

    fun setNegativeButtonText(@StringRes stringRes: Int): ConfirmDialog {
        negativeButtonStringRes = stringRes
        return this
    }

    fun setExtraButton(title: String, callback: () -> Unit): ConfirmDialog {
        extraButtonText = title
        onExtraButtonClick = callback
        return this
    }

    override fun show() {
        val positiveButtonText = positiveButtonText ?: context.getString(positiveButtonStringRes)
        val negativeButtonText = negativeButtonText ?: context.getString(negativeButtonStringRes)

        val builder = makeBuilder()
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText) { _, _ -> confirm() }
                .setNegativeButton(negativeButtonText) { _, _ -> reject() }

        if (extraButtonText != null) {
            builder.setNeutralButton(extraButtonText) { _, _ -> onExtraButtonClick() }
        }

        dialog = builder.show()

        if (cancelable) {
            addOnCloseListener(this::reject)
        }

        finalizeShow(dialog)
    }

    private fun confirm() {
        if (!confirmed && !rejected) {
            onConfirm()
            confirmed = true
        }
    }

    private fun reject() {
        if (!rejected && !confirmed) {
            onReject()
            rejected = true
        }
    }
}

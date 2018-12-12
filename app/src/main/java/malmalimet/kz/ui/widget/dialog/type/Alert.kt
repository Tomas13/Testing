package kz.senim.ui.dialog.type

import android.app.AlertDialog
import android.support.v4.content.ContextCompat
import android.text.Html
import android.widget.TextView
import kz.senim.common.extensions.dimen
import kz.senim.common.extensions.dpToPx
import malmalimet.kz.R
import malmalimet.kz.utils.FormattingUtils

/**
 * Simplest form of dialog, with a message and a single dismiss button.
 */
class Alert : BaseDialog(), Dialog {

    private var message: CharSequence? = null
    private var buttonText: String? = null

    private lateinit var dialog: AlertDialog

    override fun setTitle(title: String?): Alert {
        super.setTitle(title)
        return this
    }

    fun setMessage(message: CharSequence): Alert {
        this.message = message
        return this
    }

    fun setCallback(callback: () -> Unit): Alert {
        addOnCloseListener(callback)
        return this
    }

    fun setButtonText(text: String): Alert {
        buttonText = text
        return this
    }

    override fun show() {

        if (buttonText == null) {
            buttonText = context.getString(R.string.action_ok)
        }

        val builder = makeBuilder()

        if (title != null) {
            val titleView = TextView(context)

            titleView.text = FormattingUtils.formatHtml("<b>$title</b>")

            val paddingHorizontal = context.dpToPx(24)
            val paddingTop = context.dimen(R.dimen.large).toInt()
            titleView.setPadding(paddingHorizontal, paddingTop, paddingHorizontal, 0)
            titleView.textSize = 18f
            titleView.setTextColor(ContextCompat.getColor(context, R.color.textColorDark))

            builder.setCustomTitle(titleView)
        }

        dialog = builder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonText, null)
                .show()

        finalizeShow(dialog)
    }
}

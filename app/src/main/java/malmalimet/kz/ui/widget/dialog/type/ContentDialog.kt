package kz.senim.ui.dialog.type

import android.app.AlertDialog
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.WindowManager
import android.widget.FrameLayout

import kz.senim.common.extensions.dimen
import kz.senim.common.extensions.dpToPx
import kz.senim.common.extensions.inflateBinding
import malmalimet.kz.BR
import malmalimet.kz.R
import malmalimet.kz.ui.widget.dialog.DialogViewModel
import malmalimet.kz.utils.UiUtils

/**
 * A dialog with a custom content defined in an XML layout file, with an optional
 * ViewModel for data binding. Has two buttons for submitting and cancelling the dialog.
 */
class ContentDialog : BaseDialog(), Dialog {

    private var viewModel: DialogViewModel<ContentDialog, *>? = null
    private var onSubmit: () -> Boolean = { true }
    private var shouldOpenKeyboard = false

    @StringRes
    private var negativeButtonText = R.string.action_cancel
    @StringRes
    private var positiveButtonText = R.string.action_ok

    @LayoutRes
    private var layoutRes: Int = 0

    private lateinit var dialog: AlertDialog

    override fun setTitle(title: String?): ContentDialog {
        super.setTitle(title)
        return this
    }

    fun setLayoutRes(@LayoutRes layoutRes: Int): ContentDialog {
        this.layoutRes = layoutRes
        return this
    }

    fun setViewModel(viewModel: DialogViewModel<ContentDialog, *>): ContentDialog {
        this.viewModel = viewModel
        return this
    }

    fun onSubmit(callback: () -> Boolean): ContentDialog {
        this.onSubmit = callback
        return this
    }

    fun setNegativeButtonText(@StringRes textRes: Int): ContentDialog {
        negativeButtonText = textRes
        return this
    }

    fun setPositiveButtonText(@StringRes textRes: Int): ContentDialog {
        positiveButtonText = textRes
        return this
    }

    fun setShouldOpenKeyboard(shouldOpenKeyboard: Boolean): ContentDialog {
        this.shouldOpenKeyboard = shouldOpenKeyboard
        return this
    }

    override fun show() {
        val builder = makeBuilder()
        builder.setTitle(title)

        val binding = context.inflateBinding<ViewDataBinding>(layoutRes)

        val container = FrameLayout(context)
        container.layoutParams = UiUtils.makeLayoutParams_MW()

        val paddingTop = context.dimen(R.dimen.large).toInt()
        val paddingHorizontal = context.dpToPx(20)
        container.setPadding(paddingHorizontal, paddingTop, paddingHorizontal, 0)

        container.addView(binding.root)

        builder.setView(container)
        builder.setNegativeButton(negativeButtonText, null)
        builder.setPositiveButton(positiveButtonText, null)

        dialog = builder.create()

        viewModel?.let {
            binding.setVariable(BR.vm, it)
            it.onStart()
            it.setDialog(this)
        }

        dialog.setOnShowListener { _ ->
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener({ submit() })
        }

        if (shouldOpenKeyboard) {
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        }

        dialog.show()
        finalizeShow(dialog)
    }

    override fun dismiss() {
        super.dismiss()
        viewModel?.apply {
            onStop()
            onDestroy()
        }
    }

    fun submit() {
        if (onSubmit()) {
            dismiss()
        }
    }
}

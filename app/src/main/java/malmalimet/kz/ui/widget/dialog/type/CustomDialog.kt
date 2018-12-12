package kz.senim.ui.dialog.type

import android.app.AlertDialog
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.annotation.LayoutRes
import android.view.Window

import kz.senim.common.extensions.inflateBinding
import malmalimet.kz.BR
import malmalimet.kz.ui.widget.dialog.DialogViewModel

/**
 * Completely custom dialog with a layout defined in XML, and logic defined
 * in a ViewModel.
 */
class CustomDialog : BaseDialog(), Dialog {

    @LayoutRes
    private var layoutId: Int = 0
    private var viewModel: DialogViewModel<CustomDialog, *>? = null
    private var onSubmit: () -> Unit = {}
    private var transparent = false

    private lateinit var dialog: AlertDialog

    fun setViewModel(viewModel: DialogViewModel<CustomDialog, *>): CustomDialog {
        this.viewModel = viewModel
        return this
    }

    fun setLayoutId(@LayoutRes layoutId: Int): CustomDialog {
        this.layoutId = layoutId
        return this
    }

    fun onSubmit(fn: () -> Unit): CustomDialog {
        this.onSubmit = fn
        return this
    }

    fun setTransparent(transparent: Boolean): CustomDialog {
        this.transparent = transparent
        return this
    }

    override fun show() {
        val builder = makeBuilder()
        val binding = context.inflateBinding<ViewDataBinding>(layoutId)

        viewModel?.let {
            binding.setVariable(BR.vm, it)
            it.onStart()
            it.setDialog(this)
        }

        builder.setView(binding.root)

        dialog = builder.create()

        if (transparent) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
        onSubmit()
        dismiss()
    }
}

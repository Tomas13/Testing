//package kz.senim.ui.dialog.type
//
//import android.app.AlertDialog
//import android.databinding.DataBindingUtil
//import android.databinding.ViewDataBinding
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.support.annotation.LayoutRes
//import android.support.annotation.StringRes
//import android.view.*
//import android.widget.FrameLayout
//import android.widget.TextView
//import kotlinx.android.synthetic.main.dialog_custom.view.*
//
//import java.util.ArrayList
//
//import kz.senim.BR
//import kz.senim.R
//import kz.senim.common.extensions.inflate
//import kz.senim.common.extensions.inflateBinding
//import kz.senim.ui.common.viewmodel.DialogViewModel
//import kz.senim.ui.common.viewmodel.ViewModel
//import kz.senim.util.UiUtils
//import rx.Observable
//import rx.subjects.PublishSubject
//
///**
// * Just like [ContentDialog] but with custom dismiss button.
// */
//open class SenimContentDialog : BaseDialog(), Dialog {
//
//    @LayoutRes
//    private var layoutId: Int = 0
//    private var viewModel: DialogViewModel<SenimContentDialog, *>? = null
//    private var showCloseButton = true
//    @StringRes
//    private var closeButtonStringRes = R.string.action_close
//
//    private var transparent = false
//
//    private val closeEventRelay: PublishSubject<Void> by lazy { PublishSubject.create<Void>() }
//
//    private lateinit var dialog: AlertDialog
//
//    private val onCloseBtnClickListener = View.OnClickListener { dismiss() }
//
//    fun setViewModel(viewModel: DialogViewModel<SenimContentDialog, *>): SenimContentDialog {
//        this.viewModel = viewModel
//        return this
//    }
//
//    fun setLayoutId(@LayoutRes layoutId: Int): SenimContentDialog {
//        this.layoutId = layoutId
//        return this
//    }
//
//    fun setShowCloseButton(showCloseButton: Boolean): SenimContentDialog {
//        this.showCloseButton = showCloseButton
//        return this
//    }
//
//    fun setCloseButtonText(@StringRes stringRes: Int): SenimContentDialog {
//        closeButtonStringRes = stringRes
//        return this
//    }
//
//    fun setTransparent(transparent: Boolean): SenimContentDialog {
//        this.transparent = transparent
//        return this
//    }
//
//    fun observeCloseEvent(): Observable<Void> {
//        return closeEventRelay
//    }
//
//    override fun show() {
//        val builder = makeBuilder()
//
//        val root = context.inflate(R.layout.dialog_custom)
//        val binding = context.inflateBinding<ViewDataBinding>(layoutId)
//
//        viewModel?.let {
//            binding.setVariable(BR.vm, it)
//            it.onStart()
//            it.setDialog(this)
//        }
//
//        root.dialog_custom_container.addView(binding.root, UiUtils.makeLayoutParams_MW())
//        builder.setView(root)
//
//        dialog = builder.create()
//
//        val negativeButton = root.negative_btn
//        if (showCloseButton) {
//            negativeButton.setOnClickListener(onCloseBtnClickListener)
//            root.negative_btn_text.text = context.getString(closeButtonStringRes)
//        } else {
//            negativeButton.visibility = View.GONE
//        }
//
//        val window = dialog.window
//        if (window != null) {
//            if (transparent) {
//                window.requestFeature(Window.FEATURE_NO_TITLE)
//                window.setBackgroundDrawableResource(R.drawable.shape_custom_dialog_transparent)
//            } else {
//                window.setBackgroundDrawableResource(R.drawable.shape_custom_dialog)
//            }
//        }
//
//        dialog.show()
//        finalizeShow(dialog)
//    }
//
//    override fun dismiss() {
//        super.dismiss()
//
//        closeEventRelay.onNext(null)
//
//        viewModel?.apply {
//            onStop()
//            onDestroy()
//        }
//    }
//}

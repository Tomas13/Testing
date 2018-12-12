//package kz.senim.ui.dialog.type
//
//import android.app.AlertDialog
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.android.synthetic.main.dialog_custom.view.*
//import kotlinx.android.synthetic.main.dialog_success.view.*
//import kz.senim.R
//import kz.senim.common.extensions.inflate
//import kz.senim.util.UiUtils
//
///**
// * A default success dialog with a text message.
// */
//class SuccessDialog : SenimContentDialog(), Dialog {
//    private var message: String? = null
//    private lateinit var dialog: AlertDialog
//
//    fun setMessage(message: String): SuccessDialog {
//        this.message = message
//        return this
//    }
//
//    override fun show() {
//        val builder = makeBuilder()
//        val root = context.inflate(R.layout.dialog_custom) as ViewGroup
//
//        val content = context.inflate(R.layout.dialog_success)
//        content.message.text = message
//
//        val layoutParams = UiUtils.makeLayoutParams_MW()
//        root.dialog_custom_container.addView(content, layoutParams)
//
//        builder.setView(root)
//
//        dialog = builder.create()
//        root.negative_btn.setOnClickListener { dialog.dismiss() }
//
//        dialog.window?.setBackgroundDrawableResource(R.drawable.shape_custom_dialog)
//
//        dialog.show()
//        finalizeShow(dialog)
//    }
//}

package kz.senim.ui.dialog.type

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.annotation.CallSuper
import android.support.annotation.StringRes
import android.view.ContextThemeWrapper
import kz.senim.common.extensions.unwrapActivity
import malmalimet.kz.R
import malmalimet.kz.ui.widget.dialog.DialogManager
import malmalimet.kz.ui.widget.dialog.DialogUtils
import javax.inject.Inject

abstract class BaseDialog : Dialog {

    @Inject
    lateinit var dialogManager: DialogManager

    @StringRes
    protected var titleRes: Int = 0
    protected var title: String? = null

    protected lateinit var context: Context
    protected var cancelable = true

    private var alertDialog: AlertDialog? = null
    private var typeId: String? = null
    private val onCloseListeners = mutableListOf<() -> Unit>()

    // Boolean flag to make sure that the OnClose listeners are only run once.
    private var onCloseListenersHaveBeenRun = false

    private val onCancelListener = DialogInterface.OnCancelListener { runOnCloseListeners() }
    private val onDismissListener = DialogInterface.OnDismissListener { runOnCloseListeners() }

    private fun runOnCloseListeners() {
        if (!onCloseListenersHaveBeenRun) {
            for (listener in onCloseListeners) {
                listener()
            }
        }
        onCloseListenersHaveBeenRun = true
    }

    override fun setContext(context: Context): BaseDialog {
        this.context = context
        return this
    }

    override fun setTitle(title: String?): BaseDialog {
        this.title = title
        return this
    }


    fun setTitleRes(@StringRes titleRes: Int): BaseDialog {
        this.titleRes = titleRes
        return this
    }

    fun setCancelable(cancelable: Boolean): BaseDialog {
        this.cancelable = cancelable
        return this
    }

    override fun setTypeId(id: String): BaseDialog {
        this.typeId = id
        return this
    }

    fun addOnCloseListener(listener: () -> Unit): BaseDialog {
        onCloseListeners.add(listener)
        return this
    }

    protected fun makeBuilder(): AlertDialog.Builder {
        return AlertDialog.Builder(ContextThemeWrapper(context, R.style.Dialog))
    }

    protected fun finalizeShow(alertDialog: AlertDialog) {
        DialogUtils.fix(alertDialog)
        alertDialog.setCancelable(cancelable)
        if (typeId != null) {
            dialogManager.onSingletonDialogShown(typeId, this)
        }

        alertDialog.setOnCancelListener(onCancelListener)
        alertDialog.setOnDismissListener(onDismissListener)

        this.alertDialog = alertDialog
    }

    @CallSuper
    override fun dismiss() {
        val activity = context.unwrapActivity()
        if (activity != null && activity.isFinishing) {
            return
        }

        if (typeId != null) {
            dialogManager.removeDialog(typeId)
        }

        alertDialog?.dismiss()

        runOnCloseListeners()
    }
}

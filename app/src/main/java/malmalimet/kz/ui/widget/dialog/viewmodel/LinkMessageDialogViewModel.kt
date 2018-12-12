//package kz.senim.ui.dialog.viewmodel
//
//import kz.senim.ui.common.viewcontract.ViewContract
//import kz.senim.ui.common.viewmodel.OldBaseViewModel
//import kz.senim.ui.common.viewmodel.DialogViewModel
//import kz.senim.ui.dialog.type.ContentDialog
//import kz.senim.ui.dialog.type.Dialog
//
//class LinkMessageDialogViewModel : OldBaseViewModel<ViewContract>(), DialogViewModel<ContentDialog, ViewContract> {
//
//    lateinit var contentDialog: Dialog
//    var message: CharSequence? = null
//
//    override fun setDialog(dialog: ContentDialog) {
//        contentDialog = dialog
//    }
//}
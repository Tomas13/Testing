//package kz.senim.ui.common.base
//
//import malmalimet.kz.ui.widget.dialog.ViewModel
//import malmalimet.kz.ui.widget.viewcontract.ViewContract
//
//abstract class BaseViewModel<V : ViewContract> : ViewModel() {
//    @Suppress("PropertyName", "UNCHECKED_CAST")
//    protected val VIEW: V?
//        get() = activity as V?
//}
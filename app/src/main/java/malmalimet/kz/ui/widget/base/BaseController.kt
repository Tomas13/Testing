//package kz.senim.ui.common.base
//
//import android.databinding.ViewDataBinding
//
//abstract class BaseController<B : ViewDataBinding, V : ViewModel> : Controller<B, V>() {
//
//    protected val app: App
//        get() = applicationContext as App
//
//    protected val injector: AppInjector
//        get() = app.appInjector
//}
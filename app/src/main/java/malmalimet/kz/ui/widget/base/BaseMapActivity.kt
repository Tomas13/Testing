//package kz.senim.ui.common.base
//
//import android.os.Bundle
//import android.support.annotation.CallSuper
//import malmalimet.kz.BuildConfig
//
//abstract class BaseMapActivity : ViewContractActivity() {
//
////    val mapView: MapView? by lazy { getMapViewId()?.let { findViewById<MapView>(it) } }
//
//    @CallSuper
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
////        MapKitFactory.setApiKey(BuildConfig.YANDEX_MAP_KIT_API_KEY)
////        MapKitFactory.initialize(this)
//    }
//
//    @CallSuper
//    override fun onStart() {
//        super.onStart()
////        mapView?.onStart()
////        MapKitFactory.getInstance().onStart()
//    }
//
//    @CallSuper
//    override fun onStop() {
//        super.onStop()
////        mapView?.onStop()
////        MapKitFactory.getInstance().onStop()
//    }
//
//    abstract fun getMapViewId(): Int?
//}
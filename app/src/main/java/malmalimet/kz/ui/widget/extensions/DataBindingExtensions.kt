package kz.senim.common.extensions

import android.databinding.Observable
import malmalimet.kz.ui.widget.LoadingVariable

fun Observable.onPropertyChanged(callback: () -> Unit): Observable.OnPropertyChangedCallback {
    val onPropertyChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            callback()
        }
    }

    addOnPropertyChangedCallback(onPropertyChangedCallback)

    return onPropertyChangedCallback
}

fun LoadingVariable.onPropertyChanged(callback: () -> Unit): Observable.OnPropertyChangedCallback {
    val onPropertyChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            callback()
        }
    }

    addOnPropertyChangedCallback(onPropertyChangedCallback)

    return onPropertyChangedCallback
}

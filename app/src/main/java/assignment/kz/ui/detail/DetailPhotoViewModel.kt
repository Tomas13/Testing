package assignment.kz.ui.detail

import android.databinding.ObservableField
import android.os.Bundle
import assignment.kz.App
import assignment.kz.data.SupermarketRepository
import assignment.kz.ui.BaseViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

const val KEY_PHOTO_ID = "photoId"

class DetailPhotoViewModel : BaseViewModel() {

    @Inject
    lateinit var supermarketRepository: SupermarketRepository

    val title = ObservableField<String>()
    val link = ObservableField<String>()

    init {
        App.getApp().getmDiComponent().inject(this)
    }

    fun extractPhotoId(arguments: Bundle?) {
        val photoId = arguments?.getString(KEY_PHOTO_ID)

        supermarketRepository.loadPhotoById(photoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    title.set(it.title)
                    link.set(it.url_o)
                }
    }
}

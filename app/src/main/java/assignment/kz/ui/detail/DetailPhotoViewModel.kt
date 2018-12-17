package assignment.kz.ui.detail

import android.databinding.ObservableField
import android.net.Uri
import android.os.Bundle
import assignment.kz.App
import assignment.kz.ui.BaseViewModel
import com.domain.GetOriginalPhoto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

const val KEY_PHOTO_ID = "photoId"

class DetailPhotoViewModel : BaseViewModel() {

    @Inject
    lateinit var getOriginalPhoto: GetOriginalPhoto

    val title = ObservableField<String>()
    val link = ObservableField<String>()

    init {
        App.getApp().getmDiComponent().inject(this)
    }

    fun extractPhotoId(arguments: Bundle?) {
        val photoId = arguments?.getString(KEY_PHOTO_ID)

        photoId?.let {
            getOriginalPhoto(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { (photo, size) ->
                        title.set(photo.title)
                        link.set(size.link)
                    }
                    .autoDispose()
        }
    }

}

package assignment.kz.ui.main

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import assignment.kz.App
import assignment.kz.data.SupermarketRepository
import assignment.kz.data.db.entity.DbPhotoEntity
import assignment.kz.data.network.model.Photo
import assignment.kz.ui.BaseViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

typealias PhotoViewModels = List<PhotoViewModel>

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var photoViewModelMapper: PhotoViewModelMapper
    @Inject
    lateinit var resources: Resources
    @Inject
    lateinit var supermarketRepository: SupermarketRepository

    val isLoading = ObservableBoolean()
    val text = MutableLiveData<String>()
    val photos: ObservableField<List<PhotoViewModel>> = ObservableField()
    val title = ObservableField<String>()

    private val photosRelay = BehaviorRelay.create<PhotoViewModels>()
    val suggestions = MutableLiveData<List<String>>()

    init {
        App.getApp().getmDiComponent().inject(this)
    }

    fun searchPhotos(text: String) {
        Timber.d("searchPhotos")
        isLoading.set(true)
        supermarketRepository.searchPhotos(text)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ t ->

                    isLoading.set(false)
                    updatePhotos(t.photos.photo)
                    savePhotosToDb(t.photos.photo)

                }, { error ->
                    isLoading.set(false)
                    error.printStackTrace()
                })
    }

    private fun updatePhotos(photo: List<Photo>) {
        val newPhotos = photo.map {
            photoViewModelMapper(it)
        }
        photosRelay.accept(newPhotos)
        val list = ArrayList<PhotoViewModel>()
        list.clear()
        list.addAll(newPhotos.toList())
        photos.set(list)
    }

    private fun savePhotosToDb(photo: List<Photo>) {
        val photoEntities = ArrayList<DbPhotoEntity>()
        photo.forEach { it ->
            photoEntities.add(DbPhotoEntity(
                    it.id, it.secret, it.server,
                    it.farm, it.title, it.url_l,
                    it.url_o, it.url_c, it.url_z,
                    it.url_n, it.url_m, it.url_q,
                    it.url_s, it.url_t, it.url_sq
            ))
        }

        supermarketRepository.insertPhotos(photoEntities)
    }

    fun getSuggestions() {
        supermarketRepository.recents.subscribe { it ->
            suggestions.postValue(it.map { it.value })
        }
    }

    fun saveToRecents(value: String) {
        supermarketRepository.insertRecent(value)
    }


    /**
     * Returns a signal that, when users tap a photo,
     * it will emit the id of the selected photo.
     */
    val onPhotoTapped: Observable<PhotoId>
        get() = photosRelay
                .switchMap {
                    // To turn a stream of PhotoViewModels into
                    // a stream of tap events on those PhotoViewModels.
                    Observable
                            .merge(it.map { it.tapAction.observe })
                            .map { it.id }
                }
                .autoClear()

}

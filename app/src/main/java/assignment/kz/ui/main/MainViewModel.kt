package assignment.kz.ui.main

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.os.Bundle
import assignment.kz.App
import assignment.kz.R
import assignment.kz.data.SupermarketRepository
import assignment.kz.data.db.RecentDatabase
import assignment.kz.data.db.entity.DbRecent
import assignment.kz.data.network.NetworkService
import assignment.kz.di.SchedulerFactory
import assignment.kz.ui.BaseViewModel
import assignment.kz.utils.AppConstants
import assignment.kz.utils.Singleton
import com.domain.*
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

typealias PhotoViewModels = List<PhotoViewModel>

internal const val KEY_QUERY = "query"

class MainViewModel() : BaseViewModel() {

    @Inject
    lateinit var queryRepository: QueryRepository
    @Inject
    lateinit var photoViewModelMapper: PhotoViewModelMapper
    @Inject
    lateinit var resources: Resources
    @Inject
    lateinit var getPhotos: GetPhotos
    @Inject
    lateinit var schedulerFactory: SchedulerFactory

    val isLoading = ObservableBoolean()
    val text = MutableLiveData<String>()

    val photos: ObservableField<List<PhotoViewModel>> = ObservableField<List<PhotoViewModel>>()

    @Inject
    lateinit var supermarketRepository: SupermarketRepository

    fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_QUERY, queryRepository.latestQueryText)
    }

    val title = ObservableField<String>()

    val networkService: NetworkService
    private val photosRelay = BehaviorRelay.create<PhotoViewModels>()

    fun loadPhotos(savedInstanceState: Bundle?) {
        val query = savedInstanceState?.getString(KEY_QUERY)
        queryRepository.putQueryText(query)
    }


    init {
        App.getApp().getmDiComponent().inject(this)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConstants.BASE_URL)
                .client(Singleton.getUserClient("Basic ${supermarketRepository.accessToken.trim()}"))
                .build()

        networkService = retrofit.create(NetworkService::class.java)


        queryRepository.queries
                .map {
                    when (it) {
                        is Search -> "\"${it.queryText}\""
                        is Recent -> resources.getString(R.string.recent)
                    }
                }
                .subscribe { title.set(it) }

        getPhotos(queries = queryRepository.queries)
                .subscribeOn(Schedulers.io())
                .observeOn(schedulerFactory.mainScheduler)
                .subscribe { result ->
                    when (result) {
                        is Busy -> isLoading.set(true)
                        is Success<Photos> -> {
                            updatePhotos(result)
                            isLoading.set(false)
                        }
                        is Failure -> {
//                            onErrorLoadingPhotosRelay.accept(Unit)
                            isLoading.set(false)
                        }
                    }
                }

    }

    fun onQueryTextSubmit(query: String?): Boolean {
        queryRepository.putQueryText(query)
        return false
    }

    private fun updatePhotos(result: Success<Photos>) {
        val newPhotos = result.value.map {
            photoViewModelMapper(it)
        }
        photosRelay.accept(newPhotos)

        val list = ArrayList<PhotoViewModel>()
        list.clear()
        list.addAll(newPhotos)
        photos.set(list)
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

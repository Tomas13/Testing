package malmalimet.kz.ui.registeranimal

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import malmalimet.kz.App
import malmalimet.kz.data.Image
import malmalimet.kz.data.SupermarketRepository
import malmalimet.kz.data.network.NetworkService
import malmalimet.kz.data.network.RemoteImage
import malmalimet.kz.data.network.model.Animal
import malmalimet.kz.data.network.model.Location
import malmalimet.kz.data.network.utils.ResponseParser
import malmalimet.kz.ui.widget.Form
import malmalimet.kz.ui.widget.select.SelectOption
import malmalimet.kz.utils.AppConstants
import malmalimet.kz.utils.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class RegisterAnimalViewModel : ViewModel() {
    //    val loadingVariable = LoadingVariable()
    val isLoading = ObservableBoolean()

    val text = MutableLiveData<String>()
    var galleryPosition = ObservableInt(0)

    val showProgress = MutableLiveData<Boolean>()

    @Inject
    lateinit var supermarketRepository: SupermarketRepository

    val networkService: NetworkService
    val images = ObservableField<List<Image>>()
    var fullSize = ObservableBoolean(false)

    init {
        App.getApp().getmDiComponent().inject(this)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConstants.BASE_URL)
                .client(Singleton.getUserClient("Basic ${supermarketRepository.accessToken.trim()}"))
                .build()

        networkService = retrofit.create(NetworkService::class.java)

        val list = ArrayList<Image>()
        list.add(Image(RemoteImage("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg")))
        list.add(Image(RemoteImage("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg")))
        list.add(Image(RemoteImage("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg")))
        images.set(list)
    }

    fun onImageClicked(image: Image) {
        val position = images.get()!!.indexOf(image)
        openImageAtIndex(position)
    }

    private fun openImageAtIndex(imageIndex: Int) {
        if (imageIndex >= 0) {
            galleryPosition.set(imageIndex)
        }
        setFullSize(true)
    }

    private fun setFullSize(value: Boolean) {
        fullSize.set(value)

//        if (value) {
//            setGalleryPositionTitle()
//            VIEW.setViewState(GalleryActivity.STATE_IMAGE)
//        } else {
//            VIEW.resetTitle()
//            VIEW.setViewState(GalleryActivity.STATE_GRID)
//        }
    }
/*
    fun getOrgByBin(bin: String) {
        isLoading.set(true)

        networkService.getOrganizationByBin(bin)
                .subscribeOn(Schedulers.io())
                .subscribe({ organization ->
                    isLoading.set(false)

                    val org = ResponseParser.getContentOrThrow(organization, "getOrgByBinRegisterAn")
                    this.bin.set(org.bin)
                    organizationName.set(org.name)
                }, { error ->
                    isLoading.set(false)
                    Timber.d(error)
                })

    }
*/

    override fun onCleared() {
        super.onCleared()

//        loadingVariable.reset()
        Timber.d("onCleared called")
    }

}

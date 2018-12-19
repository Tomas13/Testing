package assignment.kz.data.network

import assignment.kz.data.network.model.Response
import assignment.kz.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

const val URLS = "url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"

interface NetworkService {

    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    fun searchPhotos(
            @Query("text") text: String? = null,
            @Query("api_key") apiKey: String = AppConstants.FLICKR_API,
            @Query("extras") extras: String = URLS,
            @Query("per_page") perPage: Int = 20
    ): Observable<Response>

}

package com.example.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal const val URLS = "url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"

internal interface FlickrApi {
    @GET("services/rest/?method=flickr.photos.getRecent&nojsoncallback=1&format=json")
    fun getRecent(
            @Query("api_key") apiKey: String,
            @Query("extras") extras: String = URLS,
            @Query("per_page") perPage: Int = 20
    ): Single<PhotosResponseEntity>

    @GET("services/rest/?method=flickr.photos.search&nojsoncallback=1&format=json")
    fun search(
            @Query("api_key") apiKey: String,
            @Query("text") text: String? = null,
            @Query("extras") extras: String = URLS,
            @Query("per_page") perPage: Int = 20
    ): Single<PhotosResponseEntity>
}

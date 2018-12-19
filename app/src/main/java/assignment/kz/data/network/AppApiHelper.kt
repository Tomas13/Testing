package assignment.kz.data.network


import assignment.kz.data.network.model.Response
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject
constructor(private val networkService: NetworkService) : ApiHelper {

    override fun searchPhotos(text: String): Observable<Response> {
        return networkService.searchPhotos(text = text)
    }

}

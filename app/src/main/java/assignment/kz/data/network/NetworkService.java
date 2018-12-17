package assignment.kz.data.network;


import assignment.kz.data.network.model.LoginResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by root on 4/18/17.
 */

public interface NetworkService {

    @GET("login")
    Observable<LoginResponse> loginUser();

}

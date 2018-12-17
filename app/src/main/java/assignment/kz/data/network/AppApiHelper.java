package assignment.kz.data.network;


import javax.inject.Inject;
import javax.inject.Singleton;

import assignment.kz.data.network.model.LoginResponse;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    NetworkService networkService;

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<LoginResponse> loginUser(String loginData) {
        return networkService.loginUser();
    }

}

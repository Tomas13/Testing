package assignment.kz.data.network;


import java.util.List;

import assignment.kz.data.network.model.LoginResponse;
import assignment.kz.data.network.utils.ContentResponse;
import rx.Observable;

/**
 * Created by root on 4/12/17.
 */

public interface ApiHelper {

    Observable<LoginResponse> loginUser(String loginData);
}

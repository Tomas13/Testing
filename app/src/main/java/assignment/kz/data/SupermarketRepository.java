package assignment.kz.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import assignment.kz.data.network.ApiHelper;
import assignment.kz.data.network.model.LoginResponse;
import assignment.kz.data.prefs.PreferencesHelper;
import rx.Observable;

/**
 * Created by root on 3/27/18.
 */

@Singleton
public class SupermarketRepository implements ApiHelper, PreferencesHelper, Repo {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public SupermarketRepository(Context context, PreferencesHelper mPreferencesHelper, ApiHelper mApiHelper) {
        this.mContext = context;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mApiHelper = mApiHelper;
    }

    @Override
    public Observable<LoginResponse> loginUser(String loginData) {
        return mApiHelper.loginUser(loginData);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }


    @Override
    public void putAccessToken(String accessToken) {
        mPreferencesHelper.putAccessToken(accessToken.trim());
    }
}

package assignment.kz.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import assignment.kz.data.db.RecentDatabase;
import assignment.kz.data.db.entity.DbRecent;
import assignment.kz.data.network.ApiHelper;
import assignment.kz.data.network.model.LoginResponse;
import assignment.kz.data.prefs.PreferencesHelper;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by root on 3/27/18.
 */

@Singleton
public class SupermarketRepository implements ApiHelper, PreferencesHelper, Repo {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    private final RecentDatabase recentDatabase;

    private CompositeSubscription mSub = new CompositeSubscription();

    @Inject
    public SupermarketRepository(Context context,
                                 PreferencesHelper mPreferencesHelper,
                                 ApiHelper mApiHelper,
                                 RecentDatabase recentDatabase) {
        this.mContext = context;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mApiHelper = mApiHelper;
        this.recentDatabase = recentDatabase;
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

    @Override
    public Observable<List<DbRecent>> getRecents() {
        return Observable.fromCallable(() -> recentDatabase.getRecentDao().getSuggestions())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void insertRecent(String value) {
        Subscription sub = insertRecent(new DbRecent(value))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        Timber.d("Successfully updated");
                    }
                });

//        mSub.add(sub);

//        recentDatabase.getRecentDao().insert(new DbRecent(value));
    }

    @Override
    public Observable<String> insertRecent(DbRecent dbRecent) {
        return Observable.fromCallable(() -> {
            recentDatabase.getRecentDao().insert(dbRecent);
            return "";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}

package assignment.kz.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import assignment.kz.data.db.RecentDatabase;
import assignment.kz.data.db.entity.DbPhotoEntity;
import assignment.kz.data.db.entity.DbRecentEntity;
import assignment.kz.data.network.ApiHelper;
import assignment.kz.data.network.model.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@Singleton
public class SupermarketRepository implements ApiHelper, Repo {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final RecentDatabase recentDatabase;

    @Inject
    public SupermarketRepository(Context context,
                                 ApiHelper mApiHelper,
                                 RecentDatabase recentDatabase) {
        this.mContext = context;
        this.mApiHelper = mApiHelper;
        this.recentDatabase = recentDatabase;
    }

    @Override
    public Observable<Response> searchPhotos(String text) {
        return mApiHelper.searchPhotos(text);
    }

    @Override
    public Observable<List<DbRecentEntity>> getRecents() {
        return Observable.fromCallable(() -> recentDatabase.getRecentDao().getSuggestions())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void insertRecent(String value) {
        insertRecent(new DbRecentEntity(value))
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
    }

    @Override
    public Observable<String> insertRecent(DbRecentEntity dbRecentEntity) {
        return Observable.fromCallable(() -> {
            recentDatabase.getRecentDao().insert(dbRecentEntity);
            return "";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public void insertPhotos(List<DbPhotoEntity> list) {
        insertAll(list)
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
    }


    @Override
    public Observable<String> insertAll(List<DbPhotoEntity> list) {
        return Observable.fromCallable(() -> {
            recentDatabase.photoDao().insertAll(list);
            return "";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<DbPhotoEntity> loadPhotoById(String photoId) {
        return Observable.fromCallable(() -> recentDatabase.photoDao().loadPhotoById(photoId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

}

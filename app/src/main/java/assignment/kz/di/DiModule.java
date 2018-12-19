package assignment.kz.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import assignment.kz.data.Repo;
import assignment.kz.data.SupermarketRepository;
import assignment.kz.data.db.RecentDatabase;
import assignment.kz.data.network.ApiHelper;
import assignment.kz.data.network.AppApiHelper;
import assignment.kz.data.network.NetworkService;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static assignment.kz.utils.AppConstants.BASE_URL;


@Module
public class DiModule {

    private final Application mApplication;

    public DiModule(Application application) {
        mApplication = application;
    }

    @Provides
    Resources getResources(Application application){
        return application.getResources();
    }


    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Repo provideRepository(SupermarketRepository supermarketRepository) {
        return supermarketRepository;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addNetworkInterceptor(new StethoInterceptor());
        client.connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);
        client.cache(cache);
        return client.build();
    }


    @Provides
    @Singleton
    NetworkService provideNetworkService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    RecentDatabase provideRecentDatabase() {
        return RecentDatabase.getInstance(mApplication.getApplicationContext());
    }

}

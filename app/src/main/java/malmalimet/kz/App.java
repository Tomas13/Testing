package malmalimet.kz;

import android.app.Application;

import com.facebook.stetho.Stetho;

import malmalimet.kz.data.network.model.User;
import malmalimet.kz.di.DaggerDiComponent;
import malmalimet.kz.di.DiComponent;
import malmalimet.kz.di.DiModule;
import malmalimet.kz.utils.ReleaseTree;
import timber.log.Timber;

/**
 * Created by root on 3/26/18.
 */

public class App extends Application {
    private DiComponent mDiComponent;

    private static App app;

    public static App getApp() {
        return app;
    }

    public static User user;

    public static User getUser() {
        return user;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        mDiComponent = DaggerDiComponent.builder()
                .diModule(new DiModule(this)).build();

        mDiComponent.inject(this);

        Stetho.initializeWithDefaults(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        } else {
            //Release mode
            Timber.plant(new ReleaseTree());
        }
    }

    public DiComponent getmDiComponent() {
        return mDiComponent;
    }
}

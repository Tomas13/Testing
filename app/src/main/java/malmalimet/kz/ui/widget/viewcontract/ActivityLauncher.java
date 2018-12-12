package malmalimet.kz.ui.widget.viewcontract;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;

public interface ActivityLauncher {
    void launchActivity(Intent intent);
    void launchActivity(Class<? extends Activity> activityClass);
    void launchActivityClearTop(Class<? extends Activity> activityClass, @Nullable Bundle data);
    void launchActivity(Class<? extends Activity> activityClass, @NonNull Bundle data);
//    Observable<ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass);
//    Observable<ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass, @NonNull Bundle data);
    void recreateActivity();

    void replaceCurrentActivity(Class<? extends Activity> activityClass);
    void replaceCurrentActivity(Class<? extends Activity> activityClass, Bundle data);

    void finishCurrentActivity();
    void finishCurrentActivity(int resultCode);
}

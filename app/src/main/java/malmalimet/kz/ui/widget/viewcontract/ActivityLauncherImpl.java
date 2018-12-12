package malmalimet.kz.ui.widget.viewcontract;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import malmalimet.kz.R;


public class ActivityLauncherImpl implements ActivityLauncher {

    private ViewContractActivity mActivity;

    public ActivityLauncherImpl(ViewContractActivity activity) {
        mActivity = activity;
    }

    @Override
    public void launchActivity(Intent intent) {
        mActivity.closeKeyboard();
        mActivity.startActivity(intent);
        overrideOpenTransition();
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass) {
        doLaunchActivity(activityClass, null);
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass, @NonNull Bundle data) {
        doLaunchActivity(activityClass, data);
    }


    @Override
    public void launchActivityClearTop(Class<? extends Activity> activityClass, Bundle data) {
        Intent intent = new Intent(mActivity, activityClass);
        if (data != null) {
            intent.putExtras(data);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        launchActivity(intent);
    }


    private void doLaunchActivity(Class<? extends Activity> activityClass, @Nullable Bundle data) {
        Intent intent = new Intent(mActivity, activityClass);
        if (data != null) {
            intent.putExtras(data);
        }

        mActivity.closeKeyboard();
        mActivity.startActivity(intent);
        overrideOpenTransition();
    }

//    @Override
//    public Observable<ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass) {
//        doLaunchActivityForResult(activityClass, null);
//        return mActivity.getActivityResultHandler().observeActivityResult();
//    }

//    @Override
//    public Observable<ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass, @NonNull Bundle data) {
//        doLaunchActivityForResult(activityClass, data);
//        return mActivity.getActivityResultHandler().observeActivityResult();
//    }

    @Override
    public void recreateActivity() {
        mActivity.finish();
        Intent intent = new Intent(mActivity, mActivity.getClass());
        mActivity.closeKeyboard();
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.none, R.anim.none);
    }

//    private void doLaunchActivityForResult(Class<? extends Activity> activityClass, @Nullable Bundle data) {
//        Intent intent = new Intent(mActivity, activityClass);
//        if (data != null) {
//            intent.putExtras(data);
//        }
//        mActivity.closeKeyboard();
//        mActivity.startActivityForResult(intent, ActivityRequestCode.ACTIVITY_LAUNCHER);
//        overrideOpenTransition();
//    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass) {
        replaceCurrentActivity(activityClass, null);
    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass, Bundle data) {
        finishCurrentActivity();
        launchActivity(activityClass, data);
    }

    @Override
    public void finishCurrentActivity() {
        mActivity.finish();
        overrideCloseTransition();
    }

    @Override
    public void finishCurrentActivity(int resultCode) {
        mActivity.setResult(resultCode);
        finishCurrentActivity();
    }

    public void overrideOpenTransition() {
        mActivity.overridePendingTransition(R.anim.activity_open_enter, R.anim.none);
    }

    public void overrideCloseTransition() {
        mActivity.overridePendingTransition(R.anim.none, R.anim.activity_close_exit);
    }
}

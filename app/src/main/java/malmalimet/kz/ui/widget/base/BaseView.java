package malmalimet.kz.ui.widget.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;
import java.io.IOException;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kz.senim.ui.dialog.type.Dialog;
import malmalimet.kz.ui.widget.viewcontract.ViewContract;
import malmalimet.kz.ui.widget.viewcontract.ViewContractActivity;
import malmalimet.kz.utils.UiUtils;

/**
 * A base view to be used in the context of a BaseActivity.
 * Provides access to the activity's contract methods.
 */
public abstract class BaseView extends FrameLayout implements ViewContract {

    private ViewContractActivity mActivity;

    public BaseView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mActivity = (ViewContractActivity) UiUtils.getActivityFromContext(getContext());
    }

    protected BaseActivity getActivity() {
        return mActivity;
    }

//    protected AppInjector getAppComponent() {
//        return mActivity.getAppInjector();
//    }

//    @Override
//    public void addBackButtonHandler(BackButtonHandler handler) {
//        mActivity.addBackButtonHandler(handler);
//    }
//
//    @Override
//    public void removeBackButtonHandler(BackButtonHandler handler) {
//        mActivity.removeBackButtonHandler(handler);
//    }

    @Override
    public void closeKeyboard() {
        mActivity.closeKeyboard();
    }

    @Override
    public void closeKeyboard(View forView) {
        mActivity.closeKeyboard(forView);
    }

    @Override
    public void openKeyboard() {
        mActivity.openKeyboard();
    }

    @Override
    public boolean viewFile(File file) {
        return mActivity.viewFile(file);
    }

    @Override
    public void logout() {
        mActivity.logout();
    }

    @Override
    public void showDialog(Dialog dialog) {
        mActivity.showDialog(dialog);
    }

    @Override
    public File shrinkImageFile(File original) throws IOException {
        return mActivity.shrinkImageFile(original);
    }


    //    @Override
//    public void showGallery(List<Image> gallery, @Nullable String title) {
//        mActivity.showGallery(gallery, title);
//    }
//
//    @Override
//    public void showGridGallery(List<Image> gallery, @Nullable String title) {
//        mActivity.showGridGallery(gallery, title);
//    }
//
//    @Override
//    public void showGridGalleryWithInitialImage(List<Image> gallery, @Nullable String title, int initialImageIndex) {
//        mActivity.showGridGalleryWithInitialImage(gallery, title, initialImageIndex);
//    }
//
//    @Override
//    public void showMap(String title, List<MapPoint> points) {
//        mActivity.showMap(title, points);
//    }
//
//    @Override
//    public Observable<GeoPointWithAddress> showOnMap(GeoPoint center) {
//        return mActivity.showOnMap(center);
//    }
//
    @Override
    public void showSnackbar(String message) {
        mActivity.showSnackbar(message);
    }

    @Override
    public void showSnackbar(@StringRes int stringRes) {
        mActivity.showSnackbar(stringRes);
    }

    @Override
    public void showDismissableSnackbar(int stringRes, String dismissButtonText) {
        mActivity.showDismissableSnackbar(stringRes, dismissButtonText);
    }

    @Override
    public void showDismissableSnackbar(String message, String dismissButtonText) {
        mActivity.showDismissableSnackbar(message, dismissButtonText);
    }

    @Override
    public void showActionSnackbar(int stringRes, String actionButtonText, Function0<Unit> action) {
        mActivity.showActionSnackbar(stringRes, actionButtonText, action);
    }

    @Override
    public void showActionSnackbar(String message, String actionButtonText, Function0<Unit> action) {
        mActivity.showActionSnackbar(message, actionButtonText, action);
    }

    @Override
    public void showToast(String message) {
        mActivity.showToast(message);
    }

    @Override
    public void showToast(@StringRes int stringRes) {
        mActivity.showToast(stringRes);
    }

    @Override
    public void showLongToast(String message) {
        mActivity.showLongToast(message);
    }

    @Override
    public void showLongToast(@StringRes int stringRes) {
        mActivity.showLongToast(stringRes);
    }

    @Override
    public void launchActivity(Intent intent) {
        mActivity.launchActivity(intent);
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass) {
        mActivity.launchActivity(activityClass);
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass, @NonNull Bundle data) {
        mActivity.launchActivity(activityClass, data);
    }

    @Override
    public void recreateActivity() {
        mActivity.recreateActivity();
    }

//    @Override
//    public Observable<ActivityResultHandler.ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass) {
//        return mActivity.launchActivityForResult(activityClass);
//    }
//
//    @Override
//    public Observable<ActivityResultHandler.ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass, @NonNull Bundle data) {
//        return mActivity.launchActivityForResult(activityClass, data);
//    }

    @Override
    public void launchActivityClearTop(Class<? extends Activity> activityClass, Bundle data) {
        mActivity.launchActivityClearTop(activityClass, null);
    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass) {
        mActivity.replaceCurrentActivity(activityClass);
    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass, Bundle data) {
        mActivity.replaceCurrentActivity(activityClass, data);
    }

    @Override
    public void finishCurrentActivity() {
        mActivity.finishCurrentActivity();
    }

    @Override
    public void finishCurrentActivity(int resultCode) {
        mActivity.finishCurrentActivity(resultCode);
    }

    //    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction) {
//        mActivity.showSmsInputView(submitSmsAction);
//    }
//
//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction) {
//        mActivity.showSmsInputView(submitSmsAction, requestSmsAction);
//    }
//
//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, String phoneNumber) {
//        mActivity.showSmsInputView(submitSmsAction, requestSmsAction, phoneNumber);
//    }
//
//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, boolean shouldRequestInitialSms) {
//        mActivity.showSmsInputView(submitSmsAction, requestSmsAction, shouldRequestInitialSms);
//    }
//

}

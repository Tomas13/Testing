package malmalimet.kz.ui.widget.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import java.io.File;
import java.io.IOException;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kz.senim.ui.dialog.type.Dialog;
import malmalimet.kz.ui.widget.viewcontract.ViewContract;
import malmalimet.kz.ui.widget.viewcontract.ViewContractActivity;

public abstract class BaseViewDelegate implements ViewContract, ViewDelegate {

    protected Context mContext;
    protected ViewContract mViewContract;

    public void onCreate(ViewContractActivity context, ViewContract viewContract) {
        mContext = context;
        mViewContract = viewContract;
    }

    @Override
    public void onDestroy() {
    }

//    @Override
//    public void addBackButtonHandler(BackButtonHandler handler) {
//        mViewContract.addBackButtonHandler(handler);
//    }

//    @Override
//    public void removeBackButtonHandler(BackButtonHandler handler) {
//        mViewContract.removeBackButtonHandler(handler);
//    }

    @Override
    public File shrinkImageFile(File original) throws IOException {
        return mViewContract.shrinkImageFile(original);
    }

    @Override
    public boolean viewFile(File file) {
        return mViewContract.viewFile(file);
    }

    @Override
    public void showToast(String message) {
        mViewContract.showToast(message);
    }

    @Override
    public void showToast(int stringRes) {
        mViewContract.showToast(stringRes);
    }

    @Override
    public void showLongToast(String message) {
        mViewContract.showLongToast(message);
    }

    @Override
    public void showLongToast(int stringRes) {
        mViewContract.showLongToast(stringRes);
    }

    @Override
    public void closeKeyboard() {
        mViewContract.closeKeyboard();
    }

    @Override
    public void closeKeyboard(View forView) {
        mViewContract.closeKeyboard(forView);
    }

    @Override
    public void openKeyboard() {
        mViewContract.openKeyboard();
    }

    //    @Override
//    public void showGallery(List<Image> gallery, @Nullable String title) {
//        mViewContract.showGallery(gallery, title);
//    }
//
//    @Override
//    public void showGridGallery(List<Image> gallery, @Nullable String title) {
//        mViewContract.showGridGallery(gallery, title);
//    }
//
//    @Override
//    public void showGridGalleryWithInitialImage(List<Image> gallery, @Nullable String title, int initialImageIndex) {
//        mViewContract.showGridGalleryWithInitialImage(gallery, title, initialImageIndex);
//    }
//
//    @Override
//    public void showMap(String title, List<MapPoint> points) {
//        mViewContract.showMap(title, points);
//    }
//
//    @Override
//    public Observable<GeoPointWithAddress> showOnMap(GeoPoint center) {
//        return mViewContract.showOnMap(center);
//    }
//
    @Override
    public void logout() {
        mViewContract.logout();
    }

    @Override
    public void showDialog(Dialog dialog) {
        mViewContract.showDialog(dialog);
    }

    @Override
    public void showSnackbar(String message) {
        mViewContract.showSnackbar(message);
    }

    @Override
    public void showSnackbar(int stringRes) {
        mViewContract.showSnackbar(stringRes);
    }

    @Override
    public void showDismissableSnackbar(int stringRes, String dismissButtonText) {
        mViewContract.showDismissableSnackbar(stringRes, dismissButtonText);
    }

    @Override
    public void showDismissableSnackbar(String message, String dismissButtonText) {
        mViewContract.showDismissableSnackbar(message, dismissButtonText);
    }

    @Override
    public void showActionSnackbar(int stringRes, String actionButtonText, Function0<Unit> action) {
        mViewContract.showActionSnackbar(stringRes, actionButtonText, action);
    }

    @Override
    public void showActionSnackbar(String message, String actionButtonText, Function0<Unit> action) {
        mViewContract.showActionSnackbar(message, actionButtonText, action);
    }

    @Override
    public void launchActivity(Intent intent) {
        mViewContract.launchActivity(intent);
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass) {
        mViewContract.launchActivity(activityClass);
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass, @NonNull Bundle data) {
        mViewContract.launchActivity(activityClass, data);
    }

    @Override
    public void launchActivityClearTop(Class<? extends Activity> activityClass, Bundle data) {
        mViewContract.launchActivityClearTop(activityClass, null);
    }

    @Override
    public void recreateActivity() {
        mViewContract.recreateActivity();
    }

//    @Override
//    public Observable<ActivityResultHandler.ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass) {
//        return mViewContract.launchActivityForResult(activityClass);
//    }
//
//    @Override
//    public Observable<ActivityResultHandler.ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass, @NonNull Bundle data) {
//        return mViewContract.launchActivityForResult(activityClass, data);
//    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass) {
        mViewContract.replaceCurrentActivity(activityClass);
    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass, Bundle data) {
        mViewContract.replaceCurrentActivity(activityClass, data);
    }

    @Override
    public void finishCurrentActivity() {
        mViewContract.finishCurrentActivity();
    }

    @Override
    public void finishCurrentActivity(int resultCode) {
        mViewContract.finishCurrentActivity(resultCode);
    }

    //    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction) {
//        mViewContract.showSmsInputView(submitSmsAction);
//    }
//
//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction) {
//        mViewContract.showSmsInputView(submitSmsAction, requestSmsAction);
//    }
//
//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, String phoneNumber) {
//        mViewContract.showSmsInputView(submitSmsAction, requestSmsAction, null);
//    }
//
//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, boolean shouldRequestInitialSms) {
//        mViewContract.showSmsInputView(submitSmsAction, requestSmsAction, shouldRequestInitialSms);
//    }
//

}

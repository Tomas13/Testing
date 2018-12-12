package malmalimet.kz.ui.widget.viewcontract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import malmalimet.kz.ui.widget.base.BaseActivity;
import kz.senim.ui.dialog.type.Dialog;
import malmalimet.kz.utils.FileUtils;
import malmalimet.kz.utils.UiUtils;
import rx.subscriptions.CompositeSubscription;

public abstract class ViewContractActivity extends BaseActivity implements ViewContract {

//    @Inject
//    ApiCaller API;
//    @Inject
//    AppInitData appInitData;
//    @Inject
//    ChatConnectionInitializer mChatConnectionInitializer;
//    @Inject
//    ContactsProvider mContactsProvider;
//    @Inject
//    EventBus mEventBus;
//    @Inject
//    Permissions mPermissions;
//    @Inject
//    PhoneBookContactsCache mPhoneBookContactsCache;
//    @Inject
//    ResourceProvider res;
//    @Inject
//    SystemUi mSystemUi;
//    @Inject
//    UserModel mUserModel;

    private ActivityLauncherImpl mActivityLauncher = new ActivityLauncherImpl(this);
    //    private EventHandler mEventHandler;
    private SnackbarHandler mSnackbarHandler;
    private ImageProcessor mImageProcessor = new ImageProcessorImpl(this);
//    private UriOpener mUriOpener = new UriOpenerImpl(this);

//    private List<BackButtonHandler> mBackButtonHandlers = new ArrayList<>();


    private ViewGroup mAndroidContent;
    //    private SmsInputView mSmsInputView;
    private boolean mSmsInputViewShown = false;

    // Whether the layout was resizable before opening an SmsInputView
    private boolean wasResizable = false;

    private CompositeSubscription mSub = new CompositeSubscription();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // See BaseActivity
//        if (isMainActivityBroughtToFront()) {
//            finish();
//            return;
//        }

//        mSnackbarHandler = new SnackbarHandlerImpl(getAppInjector(), this);
//        mEventHandler = new EventHandler(getAppInjector(), this);

//        UtilsKt.delay(() -> {
//            mSub.add(mUserModel.subscribeToUser(this::handleUserChange));
//
//            mSub.add(API.create(appInitData.ensureDataAvailable())
//                    .onSuccess(checkUpdateResponse -> {
//                        if (checkUpdateResponse != null) {
//                            // Use delay so that any thrown exceptions are not caught inside ApiCaller.
//                            UtilsKt.delay(() -> {
//                                handleUpdateCheckResult(checkUpdateResponse, () -> {
//                                    onAppDataInitialized(savedInstanceState);
//                                    return Unit.INSTANCE;
//                                });
//                                return Unit.INSTANCE;
//                            });
//                        } else {
//                            // Use delay so that any thrown exceptions are not caught inside ApiCaller.
//                            UtilsKt.delay(() -> {
//                                onAppDataInitialized(savedInstanceState);
//                                return Unit.INSTANCE;
//                            });
//                        }
//                    })
//                    .call());
//            return Unit.INSTANCE;
//        });
    }

    // FIXME: make abstract and implement in every activity.
    protected void onAppDataInitialized(@Nullable Bundle savedInstanceState) {
    }

//    private void handleUpdateCheckResult(@NonNull CheckUpdateResponse update, Function0<Unit> callback) {
//        if (update.hasUpdate() && update.url != null) {
//            if (update.isCritical()) {
//                showCriticalUpdateDialog(update);
//            } else {
//                showRegularUpdateDialog(update, callback);
//            }
//        } else {
//            callback.invoke();
//        }
//    }

//    private void showCriticalUpdateDialog(@NonNull CheckUpdateResponse update) {
//        showDialog(new Alert()
//                .setTitle(res.getString(R.string.label_attention))
//                .setMessage(getUpdateMessage(update))
//                .setButtonText(res.getString(R.string.action_goto_playstore))
//                .setCallback(() -> {
//                    openPlayStore(update.url);
//                    finishCurrentActivity();
//                    return Unit.INSTANCE;
//                })
//                .setCancelable(false)
//        );
//    }

//    private void showRegularUpdateDialog(@NonNull CheckUpdateResponse update, Function0<Unit> callback) {
//        showDialog(new ConfirmDialog()
//                .setTitle(res.getString(R.string.label_attention))
//                .setMessage(getUpdateMessage(update))
//                .setPositiveButtonText(R.string.action_goto_playstore)
//                .onConfirm(() -> {
//                    openPlayStore(update.url);
//                    UtilsKt.delay(callback);
//                    return Unit.INSTANCE;
//                })
//                .onReject(callback)
//                .setCancelable(true)
//        );
//    }

//    private String getUpdateMessage(@NonNull CheckUpdateResponse update) {
//        if (update.text != null) {
//            return update.text;
//        } else {
//            if (update.isCritical()) {
//                return res.getString(R.string.help_update_critical);
//            } else {
//                return res.getString(R.string.help_update_regular);
//            }
//        }
//    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeKeyboard();
        mSub.clear();
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
//        mEventHandler.start();

//        if (mSmsInputViewShown) {
//            mSmsInputView.onStart();
//        }
    }

//    private void handleUserChange(User user) {
//        if (user != null) {
//            // Need to get contacts only after user is retrieved so that the current user is removed
//            // from the contacts list.
//            if (mPermissions.check(Manifest.permission.READ_CONTACTS) && mPhoneBookContactsCache.isEmpty()) {
//                Timber.d("Pre-caching phone book contacts");
//                mSub.add(mContactsProvider.getContactsWithPhoneNumbers()
//                        .subscribe());
//            }
//        }
//    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
//        mEventHandler.stop();

//        if (mSmsInputViewShown) {
//            mSmsInputView.onStop();
//        }
    }

    protected void clearSubscriptions() {
        mSub.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mChatConnectionInitializer != null) {
//            mChatConnectionInitializer.resumeConnectionIfExists();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (mChatConnectionInitializer != null) {
//            mChatConnectionInitializer.pauseConnectionIfExists();
//        }
    }

    @Override
    public void finish() {
        super.finish();
//        mActivityLauncher.overrideCloseTransition();
    }

    @Override
    public void onBackPressed() {
        if (!isBackPressHandled()) {
            super.onBackPressed();
        }
    }

    protected boolean isBackPressHandled() {
        if (!invokeBackButtonHandlers()) {
            return false;
        }
        return true;
    }

    private boolean invokeBackButtonHandlers() {
//        for (BackButtonHandler handler : mBackButtonHandlers) {
//            if (handler.onBackPressed()) {
//                return true;
//            }
//        }
        return false;
    }

    protected boolean isSmsInputViewShown() {
        return mSmsInputViewShown;
    }

//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction) {
//        showSmsInputView(submitSmsAction, null, null, true);
//    }

//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction) {
//        showSmsInputView(submitSmsAction, requestSmsAction, null, true);
//    }

//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, boolean shouldRequestInitialSms) {
//        showSmsInputView(submitSmsAction, requestSmsAction, null, shouldRequestInitialSms);
//    }
//
//    @Override
//    public void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, String phoneNumber) {
//        showSmsInputView(submitSmsAction, requestSmsAction, phoneNumber, true);
//    }
//
//    private void showSmsInputView(
//            SmsInputView.SubmitSmsAction submitSmsAction,
//            SmsInputView.RequestSmsAction requestSmsAction,
//            String phoneNumber,
//            boolean shouldRequestInitialSms
//    ) {
//
//        if (mSmsInputViewShown) {
//            return;
//        }
//
//        if (mAndroidContent == null) {
//            ViewGroup content = findViewById(android.R.id.content);
//            mAndroidContent = (ViewGroup) content.getChildAt(0);
//        }
//
//        if (mSmsInputView == null) {
//            mSmsInputView = new SmsInputView(this);
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            mSmsInputView.setLayoutParams(layoutParams);
//
//            mSmsInputView.setOnCloseListener(this::closeSmsInputView);
//        }
//
//        if (phoneNumber != null) {
//            mSmsInputView.setPhoneNumber(phoneNumber);
//        }
//
//        closeKeyboard();
//        TimerUtils.setTimeout(() -> mAndroidContent.addView(mSmsInputView));
//
//        mSmsInputView.setSubmitSmsAction(submitSmsAction);
//        if (requestSmsAction != null) {
//            mSmsInputView.setRequestSmsAction(requestSmsAction);
//        }
//
//        if (shouldRequestInitialSms) {
//            mSmsInputView.requestInitialSms();
//        }
//
//        mSmsInputViewShown = true;
//    }
//
//    @Override
//    public void closeSmsInputView() {
//        closeKeyboard();
//        mAndroidContent.removeView(mSmsInputView);
//        mSmsInputViewShown = false;
//    }

    public SnackbarHandler getSnackbarHandler() {
        return mSnackbarHandler;
    }

//    public void addBackButtonHandler(BackButtonHandler handler) {
//        // Prepend so that the last added handler is invoked first.
//        if (mBackButtonHandlers.contains(handler)) {
//            mBackButtonHandlers.remove(handler);
//        }
//        mBackButtonHandlers.add(0, handler);
//    }

//    public void removeBackButtonHandler(BackButtonHandler handler) {
//        mBackButtonHandlers.remove(handler);
//    }

    @Override
    public void showDialog(Dialog dialog) {
        dialog.setContext(this)
//                .setAppComponent(getAppInjector())
                .show();
    }

    @Override
    public void showSnackbar(String message) {
        mSnackbarHandler.showSnackbar(message);
    }

    @Override
    public void showSnackbar(int stringRes) {
        mSnackbarHandler.showSnackbar(stringRes);
    }

    @Override
    public void showDismissableSnackbar(String message, String dismissButtonText) {
        mSnackbarHandler.showDismissableSnackbar(message, dismissButtonText);
    }

    @Override
    public void showDismissableSnackbar(int stringRes, String dismissButtonText) {
        mSnackbarHandler.showDismissableSnackbar(stringRes, dismissButtonText);
    }

    @Override
    public void showActionSnackbar(String message, String actionButtonText, Function0<Unit> action) {
        mSnackbarHandler.showActionSnackbar(message, actionButtonText, action);
    }

    @Override
    public void showActionSnackbar(int stringRes, String actionButtonText, Function0<Unit> action) {
        mSnackbarHandler.showActionSnackbar(stringRes, actionButtonText, action);
    }

    @Override
    public void showToast(String message) {
        mSnackbarHandler.showToast(message);
    }

    @Override
    public void showToast(int messageRes) {
        mSnackbarHandler.showToast(messageRes);
    }

    @Override
    public void showLongToast(String message) {
        mSnackbarHandler.showLongToast(message);
    }

    @Override
    public void showLongToast(int messageRes) {
        mSnackbarHandler.showLongToast(messageRes);
    }

    @Override
    public File shrinkImageFile(File original) throws IOException {
        return mImageProcessor.shrinkImageFile(original);
    }

    @Override
    public boolean viewFile(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri = FileUtils.getContentUriForFile(this, file);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType != null) {
            intent.setDataAndType(uri, mimeType);
        } else {
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (intent.resolveActivity(getPackageManager()) != null) {
            mActivityLauncher.launchActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    public void closeKeyboard() {
        closeKeyboard(getCurrentFocus());
    }

    @Override
    public void closeKeyboard(View forView) {
        IBinder windowToken = forView != null ? forView.getWindowToken() : getWindow().getDecorView().getRootView().getWindowToken();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    @Override
    public void openKeyboard() {
        View currentFocus = getCurrentFocus();
        UiUtils.openSoftKeyboard(currentFocus);
    }

//    @Override
//    public void showGallery(List<Image> gallery, @Nullable String title) {
//        doShowGallery(gallery, title, false, -1);
//    }

//    @Override
//    public void showGridGallery(List<Image> gallery, @Nullable String title) {
//        doShowGallery(gallery, title, true, -1);
//    }

//    @Override
//    public void showGridGalleryWithInitialImage(List<Image> gallery, @Nullable String title, int initialImageIndex) {
//        doShowGallery(gallery, title, true, initialImageIndex);
//    }

//    private void doShowGallery(List<Image> gallery, @Nullable String title, boolean showGrid, int initialImageIndex) {
//        ArrayList<Image> images;
//        if (gallery instanceof ArrayList) {
//            images = (ArrayList<Image>) gallery;
//        } else {
//            images = new ArrayList<>();
//            images.addAll(gallery);
//        }
//
//        Bundle data = new Bundle();
//
//        data.putSerializable(GalleryActivity.EXTRA_IMAGES, images);
//        data.putBoolean(GalleryActivity.EXTRA_NO_GRID, !showGrid);
//        if (title != null) {
//            data.putString(GalleryActivity.EXTRA_TITLE, title);
//        }
//        if (initialImageIndex >= 0) {
//            data.putInt(GalleryActivity.EXTRA_INITIAL_IMAGE_INDEX, initialImageIndex);
//        }
//
//        launchActivity(GalleryActivity.class, data);
//    }

//    @Override
//    public void showMap(String title, List<MapPoint> points) {
//        Bundle data = new Bundle();
//        data.putParcelableArray(MapActivity.EXTRA_POINTS, points.toArray(new MapPoint[points.size()]));
//        data.putString(MapActivity.EXTRA_TITLE, title);
//        launchActivity(MapActivity.class, data);
//    }

//    @Override
//    public Observable<GeoPointWithAddress> showOnMap(GeoPoint center) {
//        Bundle data = new Bundle();
//        data.putParcelable(ShowOnMapActivity.EXTRA_POINT, center);
//
//        return mActivityLauncher.launchActivityForResult(ShowOnMapActivity.class, data)
//                .map(activityResult -> {
//                    if (activityResult.resultCode == Activity.RESULT_OK) {
//                        Intent intentData = activityResult.intentData;
//                        GeoPoint point = intentData.getParcelableExtra(ShowOnMapActivity.EXTRA_POINT);
//                        String address = intentData.getStringExtra(ShowOnMapActivity.EXTRA_ADDRESS);
//                        if (point != null && address != null) {
//                            return new GeoPointWithAddress(point, address);
//                        }
//                    }
//
//                    return null;
//                });
//    }

    @Override
    public void logout() {
//        mEventBus.send(new CleanupEvent());
//        appInitData.clear();
//        UtilsKt.delay(() -> {
//            MainActivityKt.doLogout(this);
//            return Unit.INSTANCE;
//        });
    }

    @Override
    public void launchActivity(Intent intent) {
        mActivityLauncher.launchActivity(intent);
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass) {
        mActivityLauncher.launchActivity(activityClass);
    }

    @Override
    public void launchActivity(Class<? extends Activity> activityClass, @NonNull Bundle data) {
        mActivityLauncher.launchActivity(activityClass, data);
    }

    @Override
    public void launchActivityClearTop(Class<? extends Activity> activityClass, @Nullable Bundle data) {
        mActivityLauncher.launchActivityClearTop(activityClass, data);
    }

    @Override
    public void recreateActivity() {
        mActivityLauncher.recreateActivity();
    }

//    @Override
//    public Observable<ActivityResultHandler.ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass) {
//        return mActivityLauncher.launchActivityForResult(activityClass);
//    }

//    @Override
//    public Observable<ActivityResultHandler.ActivityResult> launchActivityForResult(Class<? extends Activity> activityClass, @NonNull Bundle data) {
//        return mActivityLauncher.launchActivityForResult(activityClass, data);
//    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass) {
        mActivityLauncher.replaceCurrentActivity(activityClass);
    }

    @Override
    public void replaceCurrentActivity(Class<? extends Activity> activityClass, Bundle data) {
        mActivityLauncher.replaceCurrentActivity(activityClass, data);
    }

    @Override
    public void finishCurrentActivity() {
        mActivityLauncher.finishCurrentActivity();
    }

    @Override
    public void finishCurrentActivity(int resultCode) {
        mActivityLauncher.finishCurrentActivity(resultCode);
    }

//    @Override
//    public void openLink(String url) {
//        mUriOpener.openLink(url);
//    }

//    @Override
//    public void openLinkInWebView(@NonNull String type) {
//        mUriOpener.openLinkInWebView(type);
//    }

//    @Override
//    public void openPlayStore(String url) {
//        mUriOpener.openPlayStore(url);
//    }

//    @NonNull
//    public abstract ActivityInjector getActivityInjector();
}

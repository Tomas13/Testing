//package malmalimet.kz.ui.widget.viewcontract;
//
//import android.support.annotation.StringRes;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import javax.inject.Inject;
//
//import kotlin.Unit;
//import kotlin.jvm.functions.Function0;
//import malmalimet.kz.ui.widget.base.BaseActivity;
//import malmalimet.kz.utils.UiUtils;
//
//public class SnackbarHandlerImpl implements SnackbarHandler {
//
//    @Inject
//    ResourceProvider mResourceProvider;
//
//    private BaseActivity mActivity;
//
//    public SnackbarHandlerImpl(AppInjector component, BaseActivity baseActivity) {
//        mActivity = baseActivity;
//        component.inject(this);
//    }
//
//    @Override
//    public void showSnackbar(String message) {
//        showDismissableSnackbar(message, "OK");
//    }
//
//    @Override
//    public void showSnackbar(@StringRes int messageRes) {
//        showSnackbar(mResourceProvider.getString(messageRes));
//    }
//
//    @Override
//    public void showDismissableSnackbar(String message, String dismissButtonText) {
//        ViewGroup root = mActivity.getRootLayout();
//        UiUtils.showDismissableSnackbar(root, message, dismissButtonText);
//    }
//
//    @Override
//    public void showDismissableSnackbar(int stringRes, String dismissButtonText) {
//        showDismissableSnackbar(mResourceProvider.getString(stringRes), dismissButtonText);
//    }
//
//    @Override
//    public void showActionSnackbar(String message, String actionButtonText, Function0<Unit> action) {
//        ViewGroup root = mActivity.getRootLayout();
//        UiUtils.showActionSnackbar(root, message, actionButtonText, action);
//    }
//
//    @Override
//    public void showActionSnackbar(int stringRes, String actionButtonText, Function0<Unit> action) {
//        showActionSnackbar(mResourceProvider.getString(stringRes), actionButtonText, action);
//    }
//
//    @Override
//    public void showToast(String message) {
//        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void showToast(int messageRes) {
//        showToast(mResourceProvider.getString(messageRes));
//    }
//
//    @Override
//    public void showLongToast(String message) {
//        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void showLongToast(int messageRes) {
//        showLongToast(mResourceProvider.getString(messageRes));
//    }
//}

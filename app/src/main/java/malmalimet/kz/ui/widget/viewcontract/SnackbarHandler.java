package malmalimet.kz.ui.widget.viewcontract;

import android.support.annotation.StringRes;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public interface SnackbarHandler {
    void showSnackbar(String message);
    void showSnackbar(@StringRes int stringRes);
    void showDismissableSnackbar(String message, String dismissButtonText);
    void showDismissableSnackbar(@StringRes int stringRes, String dismissButtonText);
    void showActionSnackbar(String message, String actionButtonText, Function0<Unit> action);
    void showActionSnackbar(@StringRes int stringRes, String actionButtonText, Function0<Unit> action);
    void showToast(String message);
    void showToast(@StringRes int messageRes);
    void showLongToast(String message);
    void showLongToast(@StringRes int messageRes);
}

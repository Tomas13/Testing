package assignment.kz.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import assignment.kz.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Utility methods for working with UI elements.
 */
public class UiUtils {

    // Duration for which a snackbar is shown.
    private static final int SNACKBAR_DURATION_MS = 3000;

    // Drawable for the error indicator on a TextField.
    private static Drawable sTextFieldErrorDrawable;

    private static int mStatusBarHeight;

    /**
     * Convert the dimension specified in DPs (density independent pixels) to device pixels.
     */
    public static float convertDpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertSpToPx(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * Open soft keyboard for the specified view.
     *
     * @param forView - something like EditText.
     */
    public static void openSoftKeyboard(View forView) {
        if (forView != null) {
            InputMethodManager imm = (InputMethodManager) forView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(forView, 0);
            }
        }
    }

    /**
     * Show a snackbar for the given view.
     *
     * @param view    - the view for the snackbar, which is used to find a suitable parent container.
     * @param message - the message to display.
     * @return The Snackbar object.
     */
    public static Snackbar showSnackbar(View view, String message) {
        Snackbar snackbar = makeSnackbar(view, message, null, null);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showDismissableSnackbar(View view, String message, String dismissButtonText) {
        Snackbar snackbar = makeSnackbar(view, message, dismissButtonText, null);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showActionSnackbar(View view, String message, String actionButtonText, Function0<Unit> action) {
        Snackbar snackbar = makeSnackbar(view, message, actionButtonText, action);
        snackbar.show();
        return snackbar;
    }

    private static Snackbar makeSnackbar(View view, String message, String buttonText, @Nullable Function0<Unit> action) {
        Snackbar snackbar = Snackbar.make(
                view,
                Html.fromHtml("<font color=\"#ffffff\">" + message + "</font>"),
                SNACKBAR_DURATION_MS);

        if (action != null) {
            snackbar.setAction(buttonText, v -> {
                if (snackbar.isShownOrQueued()) {
                    action.invoke();
                }
            }).setActionTextColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
        } else if (buttonText != null) {
            snackbar.setAction(buttonText, v -> {
                if (snackbar.isShownOrQueued()) {
                    snackbar.dismiss();
                }
            }).setActionTextColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
        }

        return snackbar;
    }

    /**
     * Show a snackbar for an indefinite duration.
     *
     * @param view    - view for the snackbar.
     * @param message - the snackbar message.
     * @return The Snackbar object.
     */
    public static Snackbar showPersistentSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(
                view,
                Html.fromHtml("<font color=\"#ffffff\">" + message + "</font>"),
                Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
        return snackbar;
    }

    public static int getStatusBarHeight(Context context) {
        if (mStatusBarHeight != 0) {
            return mStatusBarHeight;
        }

        Activity activity = UiUtils.getActivityFromContext(context);
        if (activity != null) {
            Rect rect = new Rect();
            Window window = activity.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            mStatusBarHeight = rect.top;
            return mStatusBarHeight;
        } else {
            return (int) context.getResources().getDimension(R.dimen.status_bar_height);
        }
    }

    public static Activity getActivityFromContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static ViewGroup.LayoutParams makeLayoutParams_MW() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static ViewGroup.LayoutParams makeLayoutParams_MM() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public static ViewGroup.LayoutParams makeLayoutParams_WW() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}

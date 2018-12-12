package malmalimet.kz.ui.widget.dialog;

import android.app.AlertDialog;
import android.view.Window;

import malmalimet.kz.ui.widget.AndroidVersion;


public class DialogUtils {

    /**
     * Fix dialog display issues on older Android versions.
     */
    public static void fix(AlertDialog alertDialog) {
        if (!AndroidVersion.isLollipop()) {
            Window window = alertDialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawableResource(android.R.color.transparent);
            }
        }
    }
}

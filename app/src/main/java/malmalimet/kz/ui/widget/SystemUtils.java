package malmalimet.kz.ui.widget;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.view.WindowManager;

/**
 * Utility methods for accessing Android system features.
 */
public class SystemUtils {

    /**
     * Return true if the network is currently available.
     *
     * @param context - any context.
     * @return True if the network is available.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Return screen dimensions, in pixels.
     *
     * @param context - any context
     * @return point.x is the screen width, and point.y is the screen height.
     */
    public static ScreenDimensions getScreenDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return new ScreenDimensions(point);
    }

    public static class ScreenDimensions {
        public int width;
        public int height;

        ScreenDimensions(Point point) {
            this.width = point.x;
            this.height = point.y;
        }

        @Override
        public String toString() {
            return "ScreenDimensions{" +
                    "width=" + width +
                    ", height=" + height +
                    '}';
        }
    }
}

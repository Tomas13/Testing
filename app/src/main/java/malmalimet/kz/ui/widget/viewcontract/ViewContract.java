package malmalimet.kz.ui.widget.viewcontract;

import android.support.annotation.Nullable;
import android.view.View;

import java.io.File;
import java.util.List;


public interface ViewContract extends
        ActivityLauncher,
        DialogHandler,
        ImageProcessor,
        SnackbarHandler{

    // Files related methods.
    boolean viewFile(File file);

    // Keyboard related methods.
    void closeKeyboard();

    void closeKeyboard(View forView);

    void openKeyboard();

    // Methods for launching custom activities.
//    void showGallery(List<Image> gallery, @Nullable String title);

//    void showGridGallery(List<Image> gallery, @Nullable String title);

//    void showGridGalleryWithInitialImage(List<Image> gallery, @Nullable String title, int initialImageIndex);

//    void showMap(String title, List<MapPoint> points);

//    Observable<GeoPointWithAddress> showOnMap(GeoPoint center);

//    void addBackButtonHandler(BackButtonHandler handler);

//    void removeBackButtonHandler(BackButtonHandler handler);

    // Miscellaneous.
    void logout();

//    void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction);

//    void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction);

//    void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, boolean shouldRequestInitialSms);

//    void showSmsInputView(SmsInputView.SubmitSmsAction submitSmsAction, SmsInputView.RequestSmsAction requestSmsAction, String phoneNumber);

//    void closeSmsInputView();
}

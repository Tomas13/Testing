package malmalimet.kz.ui.widget.viewcontract;

import android.support.annotation.NonNull;

public interface UriOpener {
    void openLink(String url);
    void openLinkInWebView(@NonNull String type);
    void openPlayStore(String url);
}

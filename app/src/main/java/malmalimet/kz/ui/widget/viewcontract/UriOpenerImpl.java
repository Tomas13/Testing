//package malmalimet.kz.ui.widget.viewcontract;
//
//import android.content.ActivityNotFoundException;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//
//import malmalimet.kz.utils.UiUtils;
//
//
//public class UriOpenerImpl implements UriOpener {
//
//    private ViewContractActivity mActivity;
//
//    public UriOpenerImpl(ViewContractActivity viewContractActivity) {
//        mActivity = viewContractActivity;
//    }
//
//    @Override
//    public void openLink(String url) {
//        UiUtils.openLink(mActivity, url);
//    }
//
//    @Override
//    public void openLinkInWebView(@NonNull String type) {
//        Bundle bundle = new Bundle();
//        bundle.putString(CustomWebViewActivity.EXTRA_TYPE, type);
//        mActivity.launchActivity(CustomWebViewActivity.class, bundle);
//    }
//
//    @Override
//    public void openPlayStore(String url) {
//        final Uri uri = Uri.parse(url);
//
//        try {
//            mActivity.launchActivity(new Intent(Intent.ACTION_VIEW, uri));
//        } catch (ActivityNotFoundException e) {
//            // Nothing.
//        }
//    }
//}

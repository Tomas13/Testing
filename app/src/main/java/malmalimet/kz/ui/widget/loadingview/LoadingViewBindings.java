package malmalimet.kz.ui.widget.loadingview;


import android.databinding.BindingAdapter;

public class LoadingViewBindings {

    @BindingAdapter("loadingView_loading")
    public static void setLoading(LoadingView loadingView, boolean loading) {
        loadingView.setLoading(loading);
    }
}

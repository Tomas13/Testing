package malmalimet.kz.ui.widget.repeater;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class RepeaterBindings {
    @BindingAdapter("repeater_items")
    public static void setItems(Repeater repeater, List items) {
        repeater.setItems(items);
    }

    @BindingAdapter("repeater_viewModel")
    public static void setViewModel(Repeater repeater, Object viewModel) {
        repeater.setViewModel(viewModel);
    }

    @BindingAdapter("repeater_endlessScroll")
    public static void addEndlessScroll(Repeater repeater, Runnable onScroll) {
        repeater.addEndlessScroll(onScroll);
    }

    @BindingAdapter("repeater_resetEndlessScroll")
    public static void resetEndlessScrollState(Repeater repeater, boolean unused) {
        repeater.resetEndlessScroll();
    }

    @BindingAdapter("repeater_scrollPosition")
    public static void setScrollPosition(Repeater repeater, int position) {
        repeater.scrollToPosition(position);
    }

    @BindingAdapter("repeater_loading")
    public static void setEndlessScrollLoading(Repeater repeater, boolean loading) {
        repeater.setLoading(loading);
    }

    @BindingAdapter("repeater_itemDecoration")
    public static void addItemDecoration(Repeater repeater, RecyclerView.ItemDecoration itemDecoration) {
        repeater.addItemDecoration(itemDecoration);
    }

    @BindingAdapter("repeater_clear")
    public static void clearItems(Repeater repeater, boolean clear) {
        repeater.clear();
    }

    @BindingAdapter("repeater_appendItems")
    public static void addItems(Repeater repeater, List items) {
        repeater.addItems(items);
    }
}

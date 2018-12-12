package malmalimet.kz.ui.widget.repeater;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import java.util.List;

import malmalimet.kz.BR;
import malmalimet.kz.R;


public class RepeaterAdapter extends DataBoundAdapter<ViewDataBinding> implements RepeaterAdapterContract {

    private static final int LOADING_SPINNER_LAYOUT = R.layout.item_repeater_loading_spinner;

    protected List items;
    private Object mViewModel;

    private boolean mLoading;
//    ItemsSwappedListener itemsSwappedListener;

    RepeaterAdapter(List items, @LayoutRes int itemLayoutId) {
        super(itemLayoutId);
        this.items = items;
    }

    @Override
    public void setViewModel(Object viewModel) {
        mViewModel = viewModel;
//        if (mViewModel instanceof ItemsSwappedListener) {
//            itemsSwappedListener = (ItemsSwappedListener) mViewModel;
//        }
    }

    @Override
    public int getItemLayoutId(int position) {
        if (position < items.size()) {
            return super.getItemLayoutId(position);
        } else {
            return LOADING_SPINNER_LAYOUT;
        }
    }

    @Override
    protected void bindItem(DataBoundViewHolder<ViewDataBinding> holder, int position, List<Object> payloads) {
        if (position < items.size()) {
            if (mViewModel != null) {
                holder.binding.setVariable(BR.vm, mViewModel);
            }
            holder.binding.setVariable(BR.item, items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mLoading) {
            // Accounting for the loading spinner.
            return items.size() + 1;
        } else {
            return items.size();
        }
    }

    void setLoading(boolean loading) {
        if (mLoading != loading) {
            mLoading = loading;
            if (loading) {
                notifyItemInserted(items.size());
            } else {
                notifyItemRemoved(items.size());
            }
        }
    }
}

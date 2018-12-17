package assignment.kz.ui.widget.repeater;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import assignment.kz.R;

public class Repeater extends RecyclerView {

    private static final int ORIENTATION_VERTICAL = 1;
    private static final int ORIENTATION_HORIZONTAL = 2;

    protected List mItems = new ArrayList();
    protected Adapter mAdapter;
    protected int itemLayoutId;

    private int mOrientation;
    private DividerItemDecoration mItemDecoration;

    private Runnable mOnScroll;
    private EndlessRecyclerViewScrollListener mEndlessScrollListener;

    private List<Function0<Unit>> mDataChangeListeners = new ArrayList<>();

    public Repeater(Context context) {
        this(context, null);
    }

    public Repeater(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        int dividerDrawableId;
        boolean scrollable;
//        boolean multiType;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Repeater, 0, 0);
        try {
            itemLayoutId = a.getResourceId(R.styleable.Repeater_repeater_itemLayout, 0);
            dividerDrawableId = a.getResourceId(R.styleable.Repeater_repeater_dividerDrawable, 0);
            scrollable = a.getBoolean(R.styleable.Repeater_repeater_scrollable, true);
//            multiType = a.getBoolean(R.styleable.Repeater_repeater_multiType, false);
            mOrientation = a.getInt(R.styleable.Repeater_repeater_orientation, ORIENTATION_VERTICAL);
        } finally {
            a.recycle();
        }

        int layoutOrientation = mOrientation == ORIENTATION_VERTICAL
                ? LinearLayoutManager.VERTICAL
                : LinearLayoutManager.HORIZONTAL;

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, layoutOrientation, false);

        if (onCreateAdapter() == null) {
//            if (multiType) {
//                mAdapter = new MultiViewRepeaterAdapter(mItems);
//                setAdapter(mAdapter);
//            } else if (itemLayoutId != 0) {
            mAdapter = new RepeaterAdapter(mItems, itemLayoutId);
            setAdapter(mAdapter);
//            }
        }

        setNestedScrollingEnabled(scrollable);
        if (!scrollable) {
            layoutManager.setAutoMeasureEnabled(true);
        }

        setLayoutManager(layoutManager);

        if (mOrientation == ORIENTATION_VERTICAL) {
            mItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

            if (dividerDrawableId != 0) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), dividerDrawableId);
                if (drawable != null) {
                    mItemDecoration.setDrawable(drawable);
                }
            }
            addItemDecoration(mItemDecoration);
        }

        mEndlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (mOnScroll != null) {
                    mOnScroll.run();
                }
            }
        };
    }

    public void addDataChangeListener(Function0<Unit> dataChangeListener) {
        mDataChangeListeners.add(dataChangeListener);
    }

    RepeaterAdapter onCreateAdapter() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public void setItems(List items) {
        mItems.clear();
        if (items != null) {
            mItems.addAll(items);
        }
        mAdapter.notifyDataSetChanged();

        scheduleAnimation();

        invokeDataChangeListeners();
    }

    public void clear() {
        mItems.clear();
        mAdapter.notifyDataSetChanged();
        scheduleAnimation();
        invokeDataChangeListeners();
    }

    @SuppressWarnings("unchecked")
    public void addItems(List items) {
        if (items != null && items.size() > 0) {
            int oldSize = mItems.size();
            mItems.addAll(items);
            mAdapter.notifyItemRangeInserted(oldSize, items.size());

            if (oldSize == 0) {
                scheduleAnimation();
            }

            invokeDataChangeListeners();
        }
    }

    private void invokeDataChangeListeners() {
        for (Function0<Unit> callback : mDataChangeListeners) {
            callback.invoke();
        }
    }

    private void scheduleAnimation() {
        LayoutAnimationController layoutAnimation = getLayoutAnimation();
        if (layoutAnimation != null) {
            scheduleLayoutAnimation();
        }
    }

    public void setViewModel(Object viewModel) {
        ((RepeaterAdapterContract) mAdapter).setViewModel(viewModel);
    }

    public void addEndlessScroll(Runnable onScroll) {
        mOnScroll = onScroll;
        if (mOnScroll == null) {
            removeOnScrollListener(mEndlessScrollListener);
        } else {
            addOnScrollListener(mEndlessScrollListener);
        }
    }

    public void resetEndlessScroll() {
        mEndlessScrollListener.resetState();
    }

    public void setCustomAdapter(Adapter adapter) {
        setAdapter(adapter);
    }

    void setLoading(boolean loading) {
        if (mAdapter instanceof RepeaterAdapter) {
            ((RepeaterAdapter) mAdapter).setLoading(loading);
        }
    }

    protected void removeDividerDecoration() {
        if (mItemDecoration != null) {
            removeItemDecoration(mItemDecoration);
        }
    }
}

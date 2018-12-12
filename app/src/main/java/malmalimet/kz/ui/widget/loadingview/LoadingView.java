package malmalimet.kz.ui.widget.loadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;

import malmalimet.kz.R;
import malmalimet.kz.utils.UiUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class LoadingView extends LinearLayout {

    private static final int LOADING_START_DELAY_MS = 400;

    private Subscription mLoadingTimer;
    private boolean mLoading = false;
    private boolean mImmediate = false;
    private boolean mNoText = false;

    private int mFadeAnimationDuration;

    public LoadingView(@NonNull Context context) {
        this(context, null);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0);
        try {
            mImmediate = a.getBoolean(R.styleable.LoadingView_loadingView_immediate, false);
            mNoText = a.getBoolean(R.styleable.LoadingView_loadingView_noText, false);
        } finally {
            a.recycle();
        }

        setBackgroundColor(ContextCompat.getColor(context, R.color.transparentOverlayWhite));

        ProgressBar progressBar = new ProgressBar(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(layoutParams);
        addView(progressBar);

        if (!mNoText) {
            TextView text = new TextView(context);
            LayoutParams textLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textLayoutParams.topMargin = (int) UiUtils.convertDpToPx(context, 16);
            text.setLayoutParams(textLayoutParams);
            text.setText(context.getString(R.string.label_loading));
            addView(text);
        }

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setVisibility(GONE);

        // NO-OP, so that click events are not passed to views below this view.
        setOnClickListener(v -> {
        });

        mFadeAnimationDuration = getResources().getInteger(R.integer.cross_fade_animation_duration);
    }

    public void setLoading(boolean loading) {
        mLoading = loading;
        if (mLoadingTimer != null) {
            mLoadingTimer.unsubscribe();
        }

        if (loading) {
            if (mImmediate) {
                setVisibility(VISIBLE);
            } else {
                scheduleLoadingTimer();
            }
        } else {
            setVisibility(GONE);
        }
    }

    private void scheduleLoadingTimer() {
        mLoadingTimer = Observable.timer(LOADING_START_DELAY_MS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> {
                    if (mLoading) {
                        setAlpha(0f);
                        setVisibility(VISIBLE);
                        animate()
                                .alpha(1f)
                                .setDuration(mFadeAnimationDuration);
                    } else {
                        setVisibility(GONE);
                    }
                });
    }
}

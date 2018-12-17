package assignment.kz.utils;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;

import rx.functions.Func0;

public class ViewBindings {

    /**
     * Show the view if the argument is true, hide otherwise.
     */
    @BindingAdapter("show")
    public static void show(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Hide the view if the argument is true, show otherwise.
     */
    @BindingAdapter("hide")
    public static void hide(View view, boolean hide) {
        view.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    /**
     * Make the view invisible (but it will still take up space) if the argument is false, make
     * it visible otherwise.
     */
    @BindingAdapter("visible")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("onLongPress")
    public static void setLongPressListener(View view, Func0<Boolean> listener) {
        if (listener != null) {
            view.setOnLongClickListener(v -> listener.call());
        } else {
            view.setOnLongClickListener(null);
        }
    }

    /**
     * Set focused state on the view. If the view happens to be an instance of EditText,
     * then open the soft keyboard once the view is focused.
     */
    @BindingAdapter("focused")
    public static void requestFocus(View view, boolean focused) {
        if (focused) {
            // Need to clear focus - fixes a bug where a keyboard cannot be opened if the view
            // already had focus.
            view.clearFocus();
            view.requestFocus();
            if (view instanceof EditText) {
                UiUtils.openSoftKeyboard(view);
//            } else if (view instanceof TextField) {
//                ((TextField) view).focus();
            }
        } else {
            view.clearFocus();
        }
    }

    @BindingAdapter("backgroundResource")
    public static void setBackgroundResource(View view, @DrawableRes int drawableRes) {
        view.setBackgroundResource(drawableRes);
    }

    @BindingAdapter("backgroundColor")
    public static void setBackgroundColor(View view, @ColorRes int colorRes) {
        if (colorRes != 0) {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorRes));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @BindingAdapter("animation")
    public static void setAnimation(View view, @AnimRes int animRes) {
        if (animRes != 0) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), animRes);
            if (animation != null) {
                view.startAnimation(animation);
            }
        }
    }

    @BindingAdapter("rotate")
    public static void rotate(View view, boolean rotate) {
        view.setRotation(rotate ? 180 : 0);
    }

    @BindingAdapter("rotateBy")
    public static void rotateBy(View view, int degrees) {
        view.setRotation(degrees);
    }

    @BindingAdapter("fl_marginTop")
    public static void setMarginTop(View view, int marginTop) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = (int) UiUtils.convertDpToPx(view.getContext(), marginTop);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("setGrayscale")
    public static void setGrayscale(View view, boolean grayscale) {
        if (grayscale) {
            // Create a paint object with 0 saturation (black and white)
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            Paint grayscalePaint = new Paint();
            grayscalePaint.setColorFilter(new ColorMatrixColorFilter(cm));
            // Create a hardware layer with the grayscale paint
            view.setLayerType(View.LAYER_TYPE_HARDWARE, grayscalePaint);
        } else {
            // Remove the hardware layer
            view.setLayerType(View.LAYER_TYPE_NONE, null);
        }
    }

//    @BindingAdapter("view_statusBarMargin")
//    public static void setStatusBarMargin(View view, boolean set) {
//        setStatusBarMargin(view, set, false);
//    }

//    @BindingAdapter("view_negativeStatusBarMargin")
//    public static void setNegativeStatusBarMargin(View view, boolean set) {
//        setStatusBarMargin(view, set, true);
//    }

//    private static void setStatusBarMargin(View view, boolean set, boolean negative) {
//        if (Version.isKitkat()) {
//            if (set) {
//                int statusBarHeight = UiUtils.getStatusBarHeight(view.getContext());
//                if (statusBarHeight > 0) {
//                    setTopMargin(view, negative ? -statusBarHeight : statusBarHeight);
//                } else {
//                    TimerUtils.setTimeout(() -> {
//                        int height = UiUtils.getStatusBarHeight(view.getContext());
//                        setTopMargin(view, negative ? -height : height);
//                    });
//                }
//            } else {
//                setTopMargin(view, 0);
//            }
//        }
//    }

    @BindingAdapter("view_bottomMargin")
    public static void setBottomMargin(View view, int margin) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.bottomMargin = margin;
        view.requestLayout();
    }

    private static void setTopMargin(View view, int margin) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = margin;
        view.setLayoutParams(layoutParams);
    }

//    @BindingAdapter("view_animatedVisible")
//    public static void setAnimatedVisibility(View view, boolean visible) {
//        int animationDuration = view.getContext().getResources().getInteger(R.integer.view_visibility_animation_duration);
//        if (visible) {
//            view.setAlpha(0f);
//            view.setVisibility(View.VISIBLE);
//            view.animate()
//                    .setListener(null)
//                    .alpha(1f)
//                    .setDuration(animationDuration);
//        } else {
//            view.animate()
//                    .alpha(0f)
//                    .setDuration(animationDuration)
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            super.onAnimationEnd(animation);
//                            view.setVisibility(View.GONE);
//                        }
//                    });
//        }
//    }

    @BindingAdapter("view_selected")
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    @BindingAdapter("view_disabled")
    public static void setDisabled(View view, boolean disabled) {
        view.setEnabled(!disabled);
    }

    @BindingAdapter("view_onScroll")
    public static void setOnScrollListener(View view, Runnable listener) {
        view.getViewTreeObserver().addOnScrollChangedListener(listener::run);
    }
}

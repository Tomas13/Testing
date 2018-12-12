package kz.senim.common.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private object DisplayMetricsCache {
    private lateinit var displayMetrics: DisplayMetrics

    fun getDisplayMetrics(resources: Resources): DisplayMetrics {
        if (!DisplayMetricsCache::displayMetrics.isInitialized) {
            displayMetrics = resources.displayMetrics
        }
        return displayMetrics
    }
}

@ColorInt
fun Context.color(@ColorRes colorRes: Int): Int =
        ContextCompat.getColor(this, colorRes)

fun Context.dimen(@DimenRes dimenRes: Int): Float =
        resources.getDimension(dimenRes)

fun Context.drawable(@DrawableRes drawableRes: Int): Drawable? =
        ContextCompat.getDrawable(this, drawableRes)

fun Context.dpToPx(dp: Float): Int =
        dpToPxF(dp).toInt()

fun Context.dpToPx(dp: Int): Int =
        dpToPxF(dp).toInt()

fun Context.dpToPxF(dp: Int): Float =
        (dp * (DisplayMetricsCache.getDisplayMetrics(resources).xdpi / DisplayMetrics.DENSITY_DEFAULT))

fun Context.dpToPxF(dp: Float): Float =
        (dp * (DisplayMetricsCache.getDisplayMetrics(resources).xdpi / DisplayMetrics.DENSITY_DEFAULT))

fun Context.spToPx(sp: Float): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)

fun Context.unwrapActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun Context.inflate(layoutId: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(layoutId, root, attachToRoot)
}

fun <T : ViewDataBinding> Context.inflateBinding(layoutId: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): T {
    return DataBindingUtil.inflate(LayoutInflater.from(this), layoutId, root, attachToRoot)
}

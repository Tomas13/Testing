package malmalimet.kz.ui.widget

import android.graphics.drawable.Drawable
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import kz.senim.common.extensions.*

fun View.dpToPx(dp: Int): Int {
    return context.dpToPx(dp)
}

fun View.dpToPx(dp: Float): Int {
    return context.dpToPx(dp)
}

fun View.dpToPxF(dp: Int): Float {
    return context.dpToPxF(dp)
}

fun View.dpToPxF(dp: Float): Float {
    return context.dpToPxF(dp)
}

fun View.spToPx(sp: Float): Float {
    return context.spToPx(sp)
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.toggle(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.toggle() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

@ColorInt
fun View.color(@ColorRes colorRes: Int): Int {
    return context.color(colorRes)
}

fun View.string(@StringRes stringRes: Int): String {
    return context.getString(stringRes)
}

fun View.drawable(@DrawableRes drawableRes: Int): Drawable? {
    return context.drawable(drawableRes)
}

fun View.safeRemoveFromParent() {
    (parent as ViewGroup?)?.removeView(this)
}

fun View.dimen(@DimenRes dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

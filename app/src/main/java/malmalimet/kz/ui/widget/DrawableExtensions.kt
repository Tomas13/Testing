package malmalimet.kz.ui.widget

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.graphics.drawable.DrawableCompat

fun Drawable.setColor(@ColorInt color: Int): Drawable {
    val wrapped = DrawableCompat.wrap(this).mutate()
    wrapped.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    return wrapped
}
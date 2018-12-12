package malmalimet.kz.ui.widget

import android.graphics.Color
import android.support.annotation.ColorInt

/**
 * Set the alpha value of this color.
 */
@ColorInt
fun Int.setAlpha(alpha: Float): Int {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)

    val alpha256 = (alpha * 255).toInt()

    return Color.argb(alpha256, red, green, blue)
}

/**
 * Darken the color by the specified amount between 0 and 1
 */
@ColorInt
fun Int.darken(amount: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] *= (1 - amount)
    return Color.HSVToColor(hsv)
}

/**
 * Lighten the color by the specified amount between 0 and 1
 */
@ColorInt
fun Int.lighten(amount: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] *= (1 + amount)
    hsv[1] *= (1 - amount)
    return Color.HSVToColor(hsv)
}

/**
 * Get the brightness of this color, between 0 and 1.
 */
fun Int.getBrightness(): Float {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)

    return (red * 299 + green * 587 + blue * 114) / 255000f
}

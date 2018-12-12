package malmalimet.kz.utils

import android.content.Context
import android.graphics.Typeface
import malmalimet.kz.R


object TypefaceUtils {
    private val typefaceCache = mutableMapOf<String, Typeface>()

    fun getTypeface(name: String): Typeface {
        if (!typefaceCache.containsKey(name)) {
            typefaceCache[name] = Typeface.create(name, Typeface.NORMAL)
        }
        return typefaceCache[name]!!
    }

    fun getBoldTypeface(context: Context): Typeface {
        return getTypeface(context.getString(R.string.font_medium))
    }

    fun getRegularTypeface(context: Context): Typeface {
        return getTypeface(context.getString(R.string.font_regular))
    }
}

package malmalimet.kz.ui.widget

import android.content.res.TypedArray
import android.support.annotation.StyleableRes
import android.util.AttributeSet
import android.view.View

fun View.parseAttributes(attrs: AttributeSet, @StyleableRes styleableId: IntArray, parse: (TypedArray) -> Unit) {
    val a = context.theme.obtainStyledAttributes(attrs, styleableId, 0, 0)
    try {
        parse(a)
    } finally {
        a.recycle()
    }
}

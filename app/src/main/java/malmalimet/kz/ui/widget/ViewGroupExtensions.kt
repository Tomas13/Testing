package malmalimet.kz.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kz.senim.common.extensions.dpToPx

fun FrameLayout.lp(width: Float, height: Float): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(context.dpToPx(width), context.dpToPx(height))
}

fun FrameLayout.lp(width: Int, height: Int): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(context.dpToPx(width), context.dpToPx(height))
}

fun ViewGroup.mergeInflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, true)
}

/**
 * Add the view to this ViewGroup at the specified index, if it is not already
 * added to it. If the view was a child of some other ViewGroup, then
 * it is first removed from it.
 */
fun ViewGroup.safeAddView(view: View, index: Int = -1) {
    if (indexOfChild(view) < 0) {
        view.safeRemoveFromParent()
        addView(view, index)
    }
}

fun ViewGroup.getChildren(): List<View> {
    val children = mutableListOf<View>()
    for (i in 0 until childCount) {
        children.add(getChildAt(i))
    }
    return children
}

const val WrapContent = ViewGroup.LayoutParams.WRAP_CONTENT
const val MatchParent = ViewGroup.LayoutParams.MATCH_PARENT

package malmalimet.kz.ui.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.graphics.ColorUtils
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import malmalimet.kz.R

private const val DEFAULT_SIZE_DP = 40
private const val ANIMATION_DURATION = 500L

class LoadingIndicator
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : View(context, attrs, defStyle), ValueAnimator.AnimatorUpdateListener {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var size = dpToPxF(DEFAULT_SIZE_DP)
    private var color = color(R.color.colorAccent)
    private var alternateColor = color(R.color.magenta)
    private lateinit var circleOval: RectF

    private var isAttached = false

    private val animator = ValueAnimator.ofFloat(0f, 360f).apply {
        duration = ANIMATION_DURATION
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
    }

    private val colorAnimator = ValueAnimator.ofFloat(0f, 1f, 1f, 0f, 0f).apply {
        duration = ANIMATION_DURATION * 4
        repeatCount = ValueAnimator.INFINITE
        startDelay = ANIMATION_DURATION * 2
        interpolator = LinearInterpolator()
    }

    init {
        if (attrs != null) {
            parseAttributes(attrs, R.styleable.LoadingIndicator) { args ->
                size = args.getDimension(R.styleable.LoadingIndicator_loadingIndicator_size, size)
                color = args.getColor(R.styleable.LoadingIndicator_loadingIndicator_color, color)
                alternateColor = args.getColor(R.styleable.LoadingIndicator_loadingIndicator_alternateColor, alternateColor)
            }
        }

        updateParameters()

        circlePaint.style = Paint.Style.STROKE
        circlePaint.color = color.setAlpha(0.2f)

        arcPaint.style = Paint.Style.STROKE
        arcPaint.color = color
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isAttached = true
        if (visibility == View.VISIBLE) {
            enableAnimation()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        isAttached = false
        disableAnimation()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == View.VISIBLE && isAttached) {
            enableAnimation()
        } else {
            disableAnimation()
        }
    }

    private fun enableAnimation() {
        animator.addUpdateListener(this)
        animator.start()
        colorAnimator.start()
    }

    private fun disableAnimation() {
        animator.removeUpdateListener(this)
        animator.cancel()
        colorAnimator.cancel()
    }

    fun setSize(sizePx: Float) {
        this.size = sizePx
        updateParameters()
    }

    fun setColors(color: Int, alternateColor: Int) {
        this.color = color
        this.alternateColor = alternateColor
    }

    private fun updateParameters() {
        val arcStrokeWidth = size / 10f

        val halfStrokeWidth = arcStrokeWidth / 2
        circleOval = RectF(
                halfStrokeWidth,
                halfStrokeWidth,
                size - halfStrokeWidth,
                size - halfStrokeWidth
        )

        circlePaint.strokeWidth = arcStrokeWidth
        arcPaint.strokeWidth = arcStrokeWidth
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
                resolveSize(size.toInt(), widthMeasureSpec),
                resolveSize(size.toInt(), heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        val blendedColor = ColorUtils.blendARGB(color, alternateColor, colorAnimator.animatedValue as Float)
        circlePaint.color = blendedColor.setAlpha(0.2f)
        arcPaint.color = blendedColor

        val currentAngle = animator.animatedValue as Float
        canvas.drawArc(circleOval, 0f, 360f, false, circlePaint)
        canvas.drawArc(circleOval, currentAngle, 90f, false, arcPaint)
    }
}
package malmalimet.kz.ui.widget

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.text.TextPaint
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import malmalimet.kz.R
import malmalimet.kz.utils.TypefaceUtils
import kotlin.math.roundToInt

const val TYPE_REGULAR = 1
const val TYPE_ROUNDED = 2
const val TYPE_FULL_WIDTH = 3

const val ICON_POSITION_LEFT = 1
const val ICON_POSITION_RIGHT = 2

@BindingAdapter("button_text")
fun setButtonText(button: Button, text: String?) {
    button.setText(text)
}

@BindingAdapter("button_loading")
fun setButtonLoading(button: Button, loading: Boolean) {
    button.setLoading(loading)
}

class Button
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : FrameLayout(context, attrs, defStyle) {

    private val button = ButtonInternal(context)
    private val loadingIndicator = LoadingIndicator(context).apply {
        hide()
        setSize(dpToPxF(24))
        setColors(Color.WHITE, Color.WHITE)
    }

    private val defaultButtonColor = color(R.color.colorAccent)

    init {

        var text: String? = null
        var buttonColor = defaultButtonColor
        var textColor = Color.WHITE
        var iconRes = 0
        var iconPosition = ICON_POSITION_LEFT
        var iconColor: Int? = null
        var type = TYPE_REGULAR
        var isSmall = false
        var buttonHeight = 0f
        var iconSize = 0f
        var padding = -1f

        if (attrs != null) {
            parseAttributes(attrs, R.styleable.Button) { args ->
                text = args.getString(R.styleable.Button_button_text)
                buttonColor = args.getColor(R.styleable.Button_button_color, buttonColor)
                textColor = args.getColor(R.styleable.Button_button_textColor, textColor)
                iconRes = args.getResourceId(R.styleable.Button_button_icon, iconRes)
                iconPosition = args.getInt(R.styleable.Button_button_iconPosition, iconPosition)
                if (args.hasValue(R.styleable.Button_button_iconColor)) {
                    iconColor = args.getColor(R.styleable.Button_button_iconColor, Color.WHITE)
                }
                type = args.getInt(R.styleable.Button_button_type, type)
                isSmall = args.getBoolean(R.styleable.Button_button_small, isSmall)
                buttonHeight = args.getDimension(R.styleable.Button_button_height, buttonHeight)
                iconSize = args.getDimension(R.styleable.Button_button_iconSize, iconSize)
                padding = args.getDimension(R.styleable.Button_button_padding, padding)
            }
        }

        button.setParams(
                text = text,
                textColor = textColor,
                iconRes = iconRes,
                iconPosition = iconPosition,
                iconColor = iconColor,
                buttonColor = buttonColor,
                type = type,
                isSmall = isSmall,
                buttonHeight = buttonHeight.roundToInt(),
                iconSize = iconSize.toInt(),
                padding = padding.toInt()
        )

        addView(button, LayoutParams(MatchParent, WrapContent))
        addView(loadingIndicator, LayoutParams(WrapContent, WrapContent).apply {
            gravity = Gravity.CENTER
            topMargin = getLoadingIndicatorTopMargin(type)
        })
    }

    fun setText(text: String?) {
        button.setText(text)
    }

    fun setParams(
            text: String? = null,
            @ColorInt textColor: Int = Color.WHITE,
            @DrawableRes iconRes: Int = 0,
            iconPosition: Int = ICON_POSITION_LEFT,
            @ColorInt iconColor: Int? = null,
            @ColorInt buttonColor: Int = defaultButtonColor,
            type: Int = TYPE_REGULAR,
            isSmall: Boolean = false,
            buttonHeight: Int = 0,
            iconSize: Int = 0,
            padding: Int = -1
    ) {
        button.setParams(
                text = text,
                textColor = textColor,
                iconRes = iconRes,
                iconPosition = iconPosition,
                iconColor = iconColor,
                buttonColor = buttonColor,
                type = type,
                isSmall = isSmall,
                buttonHeight = buttonHeight,
                iconSize = iconSize,
                padding = padding
        )

        val lp = loadingIndicator.layoutParams as LayoutParams
        lp.topMargin = getLoadingIndicatorTopMargin(type)
        loadingIndicator.layoutParams = lp
    }

    private fun getLoadingIndicatorTopMargin(type: Int): Int {
        return if (type == TYPE_FULL_WIDTH) {
            dpToPx(HALO_WIDTH_DP) / 2
        } else 0
    }

    fun setLoading(loading: Boolean) {
        button.setLoading(loading)
        loadingIndicator.toggle(loading)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        button.isEnabled = enabled
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }

    private class ButtonInternal(context: Context) : View(context), ValueAnimator.AnimatorUpdateListener {

        private var buttonColor = 0
        private var pressedButtonColor = 0
        private val disabledButtonColor = color(R.color.inactive)
        private var iconDrawable: Drawable? = null
        private var iconPosition = ICON_POSITION_LEFT
        private var type = TYPE_REGULAR

        private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = spToPx(TEXT_SIZE_SP)
        }
        private val buttonPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        }
        private val haloPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        }
        private var text: String? = null
        private var isSmall = false

        private var padding = dpToPx(PADDING_DP)
        private val haloWidth = dpToPxF(HALO_WIDTH_DP)
        private val iconMargin = dpToPx(ICON_MARGIN_DP)

        private var buttonHeight = 0
        private var cornerRadius = 0f
        private var haloCornerRadius = 0f
        private var iconSize = 0

        private val rect = RectF()
        private val textBounds = Rect()
        private var textHeight = 0

        private var isDown = false
        private var isLoading = false
        private var isDisabled = false

        private var haloAnimator: ValueAnimator? = null

        private val screenDimensions = SystemUtils.getScreenDimensions(context)

        fun setParams(
                text: String?,
                @ColorInt textColor: Int,
                @DrawableRes iconRes: Int,
                iconPosition: Int = ICON_POSITION_LEFT,
                @ColorInt iconColor: Int?,
                @ColorInt buttonColor: Int,
                type: Int,
                isSmall: Boolean,
                buttonHeight: Int,
                iconSize: Int,
                padding: Int
        ) {

            this.buttonHeight = if (buttonHeight > 0) {
                buttonHeight
            } else {
                if (type == TYPE_FULL_WIDTH) {
                    dpToPx(FULL_WIDTH_BUTTON_HEIGHT_DP)
                } else if (isSmall) {
                    dpToPx(SMALL_BUTTON_HEIGHT_DP)
                } else {
                    dpToPx(BUTTON_HEIGHT_DP)
                }
            }

            this.cornerRadius = if (type == TYPE_ROUNDED) {
                this.buttonHeight / 2f
            } else if (type == TYPE_REGULAR) {
                dpToPxF(CORNER_RADIUS_DP)
            } else {
                0f
            }

            this.haloCornerRadius = if (type == TYPE_ROUNDED) {
                (this.buttonHeight + haloWidth * 2f) / 2f
            } else {
                this.cornerRadius * 1.5f
            }

            this.iconSize = if (iconSize > 0) {
                iconSize
            } else if (isSmall) {
                dpToPx(SMALL_ICON_SIZE_DP)
            } else {
                dpToPx(ICON_SIZE_DP)
            }

            this.text = if (isSmall) text else text?.toUpperCase()
            var haloColor = 0

            this.buttonColor = buttonColor
            this.pressedButtonColor = if (buttonColor.getBrightness() > 0.6f) {
                if (buttonColor == Color.WHITE) {
                    haloColor = color(R.color.colorAccent)
                            .setAlpha(HALO_ALPHA)
                    Color.WHITE
                } else {
                    buttonColor.darken(0.1f)
                }
            } else {
                buttonColor.lighten(0.1f)
            }

            if (haloColor == 0) {
                haloPaint.color = pressedButtonColor.setAlpha(HALO_ALPHA)
            } else {
                haloPaint.color = haloColor
            }

            textPaint.color = textColor

            if (iconRes != 0) {
                iconDrawable = drawable(iconRes)
                this.iconPosition = iconPosition

                if (iconColor != null) {
                    iconDrawable = iconDrawable?.setColor(iconColor)
                }
            }

            if (padding != -1) {
                this.padding = padding
            }

            this.type = type
            this.isSmall = isSmall

            if (isSmall) {
                textPaint.typeface = TypefaceUtils.getRegularTypeface(context)
            } else {
                textPaint.typeface = TypefaceUtils.getBoldTypeface(context)
            }

            val bounds = Rect()
            textPaint.getTextBounds("X", 0, 1, bounds)
            textHeight = bounds.height()

            requestLayout()
        }

        fun setText(text: String?) {
            this.text = if (isSmall) text else text?.toUpperCase()
            requestLayout()
        }

        override fun setEnabled(enabled: Boolean) {
            super.setEnabled(enabled)
            if (isDisabled != !enabled) {
                isDisabled = !enabled
                isDown = false

                invalidate()
            }
        }

        override fun onAnimationUpdate(animation: ValueAnimator?) {
            invalidate()
        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            if (type == TYPE_REGULAR || type == TYPE_ROUNDED) {
                measureRegularButton(widthMeasureSpec, heightMeasureSpec)
            } else {
                measureFullWidthButton(widthMeasureSpec, heightMeasureSpec)
            }
        }

        private fun measureRegularButton(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            val width = calculateRequiredWidth().toInt()
            val height = (buttonHeight + haloWidth * 2).toInt()

            setMeasuredDimension(
                    resolveSize(width, widthMeasureSpec),
                    resolveSize(height, heightMeasureSpec)
            )
        }

        private fun measureFullWidthButton(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            setMeasuredDimension(
                    resolveSize(screenDimensions.width, widthMeasureSpec),
                    resolveSize(buttonHeight + haloWidth.toInt(), heightMeasureSpec)
            )
        }

        override fun onDraw(canvas: Canvas) {
            val w = measuredWidth.toFloat()
            val h = measuredHeight.toFloat()

            drawHalo(canvas, w, h)

            drawButton(canvas, w, h)
            drawTextAndIcon(canvas, w, h)
        }

        private fun getButtonColor(): Int {
            return if (isLoading || isDisabled) {
                disabledButtonColor
            } else if (isDown) {
                pressedButtonColor
            } else {
                buttonColor
            }
        }

        private fun drawHalo(canvas: Canvas, width: Float, height: Float) {
            val haloSize = haloWidth * (haloAnimator?.animatedValue as Float? ?: 0f)
            if (haloSize > 0) {
                val haloInset = haloWidth - haloSize
                rect.set(
                        if (type == TYPE_FULL_WIDTH) 0f else haloInset,
                        haloInset,
                        if (type == TYPE_FULL_WIDTH) width else width - haloInset,
                        if (type == TYPE_FULL_WIDTH) height else height - haloInset
                )

                canvas.drawRoundRect(rect, haloCornerRadius, haloCornerRadius, haloPaint)
            }
        }

        private fun drawButton(canvas: Canvas, w: Float, h: Float) {
            buttonPaint.color = getButtonColor()
            if (type == TYPE_FULL_WIDTH) {
                rect.set(0f, haloWidth, w, h)
            } else {
                rect.set(haloWidth, haloWidth, w - haloWidth, h - haloWidth)
            }
            canvas.drawRoundRect(rect, cornerRadius, cornerRadius, buttonPaint)
        }

        private fun calculateRequiredWidth(): Float {
            text?.let { text ->
                textPaint.getTextBounds(text, 0, text.length, textBounds)
            }

            val iconWidth = if (iconDrawable != null) {
                iconSize + iconMargin
            } else 0

            return haloWidth * 2 + padding * 2 + textBounds.width() + iconWidth
        }

        private fun drawTextAndIcon(canvas: Canvas, w: Float, h: Float) {
            if (!isLoading) {
                val text = text ?: return

                textPaint.getTextBounds(text, 0, text.length, textBounds)

                val requiredWidth = calculateRequiredWidth()

                val textY = haloWidth + (buttonHeight + textHeight) / 2

                val textX = if (requiredWidth < w - 20) {
                    // Draw text in the middle if there's enough horizontal space
                    (w - textBounds.width()) / 2
                } else {
                    if (iconPosition == ICON_POSITION_RIGHT) {
                        haloWidth + padding
                    } else {
                        w - haloWidth - padding - textBounds.width()
                    }
                }

                canvas.drawText(text, textX, textY, textPaint)

                iconDrawable?.let { iconDrawable ->

                    val y = haloWidth.toInt() + (buttonHeight - iconSize) / 2
                    val x = if (iconPosition == ICON_POSITION_RIGHT) {
                        (w - haloWidth - padding - iconSize).toInt()
                    } else {
                        (textX - iconMargin - iconSize).toInt()
                    }

                    iconDrawable.setBounds(x, y, x + iconSize, y + iconSize)
                    iconDrawable.draw(canvas)
                }
            }
        }

        fun setLoading(loading: Boolean) {
            if (isLoading != loading) {
                isLoading = loading

                isDown = false

                invalidate()
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent): Boolean {

            if (isLoading || isDisabled) {
                return true
            }

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isDown = true
                    haloAnimator = initHaloAnimator()
                    haloAnimator?.start()
                }
                MotionEvent.ACTION_MOVE -> {
                    if (isDown) {
                        val x = event.x
                        val y = event.y
                        // Cancel down state as soon as the pointer leaves the button.
                        if (x < this.left || x > this.right || y < this.top || y > this.bottom) {
                            isDown = false
                            haloAnimator?.reverse()
                            return false
                        }
                    }
                }
                MotionEvent.ACTION_CANCEL -> {
                    if (isDown) {
                        isDown = false
                        haloAnimator?.reverse()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (isDown) {
                        isDown = false
                        haloAnimator?.reverse()

                        val x = event.x
                        val y = event.y
                        if (x >= this.left && x <= this.right && y >= this.top && y <= this.bottom) {
                            performClick()
                        }
                    }
                }
            }

            return true
        }

        private fun initHaloAnimator(): ValueAnimator {
            haloAnimator?.removeUpdateListener(this)
            haloAnimator?.cancel()

            return ValueAnimator.ofFloat(0f, 1f).apply {
                duration = HALO_ANIMATION_DURATION
                startDelay = 0
                interpolator = DecelerateInterpolator()
                addUpdateListener(this@ButtonInternal)
            }
        }
    }
}

private const val HALO_WIDTH_DP = 6
private const val HALO_ANIMATION_DURATION = 150L
private const val HALO_ALPHA = 0.25f

private const val PADDING_DP = 18
private const val BUTTON_HEIGHT_DP = 44
private const val SMALL_BUTTON_HEIGHT_DP = 34
private const val FULL_WIDTH_BUTTON_HEIGHT_DP = 58

private const val TEXT_SIZE_SP = 14f
private const val ICON_SIZE_DP = 24
private const val SMALL_ICON_SIZE_DP = 18

private const val CORNER_RADIUS_DP = 4
private const val ICON_MARGIN_DP = 8

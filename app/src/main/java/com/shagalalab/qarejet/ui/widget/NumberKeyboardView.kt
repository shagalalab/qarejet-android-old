package com.shagalalab.qarejet.ui.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.shagalalab.qarejet.R
import kotlinx.android.synthetic.main.view_number_keyboard.view.*

class NumberKeyboardView : FrameLayout {
    private lateinit var listener: NumberVisibilityListener
    private var isVisible = true
    private val animationDuration = 500L

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val view = View.inflate(context, R.layout.view_number_keyboard, null)
        addView(view)
    }

    fun setNumberListener(listener: NumberVisibilityListener) {
        this.listener = listener

        numberKeyboardShowHide.setOnClickListener {
            val keyboardHeight = numberKeyboardPanel.height.toFloat()
            val from : Float
            val to : Float
            numberKeyboardShowHide.isEnabled = false
            listener.onNumberKeyboardChange(isVisible, keyboardHeight, animationDuration)
            if (isVisible) {
                from = 0f
                to = -keyboardHeight
                isVisible = false
            } else {
                from = -keyboardHeight
                to = 0f
                isVisible = true
            }
            ValueAnimator.ofFloat(from, to).apply {
                duration = animationDuration
                addUpdateListener {
                    val size = it.animatedValue as Float
                    numberKeyboardPanel.y = size
                    numberKeyboardShowHide.y = size + keyboardHeight
                }
                addListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        numberKeyboardShowHide.isEnabled = true
                        numberKeyboardShowHide.setImageResource(
                                if (isVisible)
                                    R.drawable.ic_keyboard_arrow_up_white_48dp
                                else
                                    R.drawable.ic_keyboard_arrow_down_white_48dp
                        )
                    }
                })
                start()
            }
        }
    }

    interface NumberVisibilityListener {
        fun onNumberKeyboardChange(isShowing: Boolean, height: Float, animationDuration: Long)
    }
}
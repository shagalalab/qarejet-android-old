package com.shagalalab.qarejet.ui.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.shagalalab.qarejet.R
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.view_number_keyboard.view.*

class NumberKeyboardView : FrameLayout {
    private lateinit var listener: NumberListener
    private val animationDuration = 500L
    private var presenter: NumberKeyboardPresenter
    private var isVisible = true

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val view = View.inflate(context, R.layout.view_number_keyboard, null)
        addView(view)
        presenter = NumberKeyboardPresenter()
    }

    fun setNumberListener(numberListener: NumberListener) {
        listener = numberListener
        setClickListeners()
        presenter.subscribe(Consumer { t -> listener.onNumberTextChanged(t) })
    }

    private fun setClickListeners() {
        numberKeyboardKey0.setOnClickListener({ presenter.handleNumber("0") })
        numberKeyboardKey1.setOnClickListener({ presenter.handleNumber("1") })
        numberKeyboardKey2.setOnClickListener({ presenter.handleNumber("2") })
        numberKeyboardKey3.setOnClickListener({ presenter.handleNumber("3") })
        numberKeyboardKey4.setOnClickListener({ presenter.handleNumber("4") })
        numberKeyboardKey5.setOnClickListener({ presenter.handleNumber("5") })
        numberKeyboardKey6.setOnClickListener({ presenter.handleNumber("6") })
        numberKeyboardKey7.setOnClickListener({ presenter.handleNumber("7") })
        numberKeyboardKey8.setOnClickListener({ presenter.handleNumber("8") })
        numberKeyboardKey9.setOnClickListener({ presenter.handleNumber("9") })
        numberKeyboardKeyDot.setOnClickListener({ presenter.handleDot() })
        numberKeyboardKeyRemove.setOnClickListener({ presenter.handleBackspace() })
        numberKeyboardShowHide.setOnClickListener({ handleShowHideAnimation() })
    }

    private fun handleShowHideAnimation() {
        val keyboardHeight = numberKeyboardPanel.height.toFloat()
        val from : Float
        val to : Float
        numberKeyboardShowHide.isEnabled = false
        listener.onNumberKeyboardChanged(isVisible, keyboardHeight, animationDuration)
        if (isVisible) {
            from = 0f
            to = -keyboardHeight
        } else {
            from = -keyboardHeight
            to = 0f
        }
        ValueAnimator.ofFloat(from, to).apply {
            duration = animationDuration
            addUpdateListener {
                val value = it.animatedValue as Float
                numberKeyboardPanel.y = value
                numberKeyboardShowHide.y = value + keyboardHeight
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
            isVisible = !isVisible
        }
    }

    interface NumberListener {
        fun onNumberKeyboardChanged(isShowing: Boolean, height: Float, animationDuration: Long)
        fun onNumberTextChanged(changedText: String)
    }
}
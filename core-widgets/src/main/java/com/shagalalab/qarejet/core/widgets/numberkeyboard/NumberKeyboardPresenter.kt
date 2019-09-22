package com.shagalalab.qarejet.core.widgets.numberkeyboard

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.res.Resources
import android.view.ViewGroup
import android.widget.ImageView
import com.shagalalab.qarejet.core.widgets.R

class NumberKeyboardPresenter(
        private val listener: NumberKeyboardView.NumberListener,
        private val resources: Resources
) {

    companion object {
        private const val INTEGER_LIMIT = 12
        private const val FRACTION_LIMIT = 2
        private const val DURATION = 500L
    }
    private var valueText = "0"
    private var isVisible = true

    fun handleNumber(number: String) {
        if (valueText.contains(".")) {
            if (valueText.substringAfter(".").length == FRACTION_LIMIT) return
        } else {
            if (valueText.length == INTEGER_LIMIT) {
                listener.onShowMessage(resources.getString(R.string.maximum_digits_reached, INTEGER_LIMIT))
                return
            }
        }

        if (valueText == "0") {
            if (number == "0") return
            valueText = number
        } else {
            valueText += number
        }
        listener.onNumberTextChanged(valueText)
    }

    fun handleBackspace() {
        if (valueText == "0") return

        valueText = valueText.dropLast(1)
        if (valueText.isEmpty()) {
            valueText = "0"
        }
        listener.onNumberTextChanged(valueText)
    }

    fun handleDot() {
        if (valueText.contains(".")) return

        valueText += "."
        listener.onNumberTextChanged(valueText)
    }

    fun handleShowHideAnimation(numberKeyboardPanel: ViewGroup, numberKeyboardShowHide: ImageView) {
        val keyboardHeight = numberKeyboardPanel.height.toFloat()
        val from: Float
        val to: Float
        numberKeyboardShowHide.isEnabled = false
        listener.onNumberKeyboardChanged(isVisible, keyboardHeight, DURATION)
        if (isVisible) {
            from = 0f
            to = -keyboardHeight
        } else {
            from = -keyboardHeight
            to = 0f
        }
        ValueAnimator.ofFloat(from, to).apply {
            duration = DURATION
            addUpdateListener {
                val value = it.animatedValue as Float
                numberKeyboardPanel.y = value
                numberKeyboardShowHide.y = value + keyboardHeight
            }
            addListener(object : AnimatorListenerAdapter() {
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
}
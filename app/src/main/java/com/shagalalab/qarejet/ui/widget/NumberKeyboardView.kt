package com.shagalalab.qarejet.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.shagalalab.qarejet.R
import kotlinx.android.synthetic.main.view_number_keyboard.view.*

class NumberKeyboardView : FrameLayout {
    private lateinit var presenter: NumberKeyboardPresenter

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val view = View.inflate(context, R.layout.view_number_keyboard, null)
        addView(view)
    }

    fun setNumberListener(numberListener: NumberListener) {
        presenter = NumberKeyboardPresenter(numberListener, context.resources)
        setClickListeners()
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
        numberKeyboardShowHide.setOnClickListener({ presenter.handleShowHideAnimation(numberKeyboardPanel, numberKeyboardShowHide) })
    }

    interface NumberListener {
        fun onNumberKeyboardChanged(isShowing: Boolean, height: Float, animationDuration: Long)
        fun onNumberTextChanged(changedText: String)
        fun onShowMessage(message: String)
    }
}
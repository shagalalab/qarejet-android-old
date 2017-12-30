package com.shagalalab.qarejet.ui.widget

import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class NumberKeyboardPresenter constructor(
    private var publishSubject : PublishSubject<String> = PublishSubject.create()
) {
    private val integerLimit = 12
    private val fractionLimit = 2
    private var valueText = "0"

    fun handleNumber(number: String) {
        if (valueText.contains(".")) {
            if (valueText.substringAfter(".").length == fractionLimit) return
        } else {
            if (valueText.length == integerLimit) return
        }

        if (valueText == "0") {
            if (number == "0") return
            valueText = number
        } else {
            valueText += number
        }
        publishSubject.onNext(valueText)
    }

    fun handleBackspace() {
        if (valueText == "0") return

        valueText = valueText.dropLast(1)
        if (valueText.isEmpty()) {
            valueText = "0"
        }
        publishSubject.onNext(valueText)
    }

    fun handleDot() {
        if (valueText.contains(".")) return

        valueText += "."
        publishSubject.onNext(valueText)
    }

    fun subscribe(observer: Consumer<String>) {
        publishSubject.subscribe(observer)
    }
}
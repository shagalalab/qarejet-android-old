package com.shagalalab.qarejet.domain.model

data class AccountWithAmount(val title: String, var amount: Double = 0.0, val currency: String) {

    fun increaseAmount(amountToAdd: Double) {
        amount += amountToAdd
    }

    fun decreaseAmount(amountToSubtract: Double) {
        amount -= amountToSubtract
    }

}

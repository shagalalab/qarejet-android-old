package com.shagalalab.qarejet.domain.model

data class CategoryWithAmount(val type: Int, val category: Category, var amount: Double) {

    fun increaseAmount(amountToBeAdded: Double) {
        amount += amountToBeAdded
    }

}
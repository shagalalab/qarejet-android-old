package com.shagalalab.qarejet.domain.model

data class CategoryWithAmount(val type: Int, val category: Category, var amount: Double, val date: Long): Comparable<CategoryWithAmount> {

    fun increaseAmount(amountToBeAdded: Double) {
        amount += amountToBeAdded
    }

    override fun compareTo(other: CategoryWithAmount) = (this.amount - other.amount).toInt()

}
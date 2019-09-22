package com.shagalalab.qarejet.ui.transaction

import com.shagalalab.qarejet.core.utils.Category
import com.shagalalab.qarejet.domain.model.Account

interface AddTransactionView {
    fun updateAccounts(accounts: List<Account>)
    fun updateCategories(categories: List<Category>)
    fun finishActivity()
    fun setSignToPlus()
    fun setSignToMinus()
    fun filterCategories(type: Int)
    fun showDateChooser()
    fun showTimeChooser()
    fun showError(message: String)
    fun showError(message: Int)
}
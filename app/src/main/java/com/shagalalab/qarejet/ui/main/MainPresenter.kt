package com.shagalalab.qarejet.ui.main

import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.util.Constants
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) {

    fun openNewTransactionScreen() {
        router.navigateTo(Constants.SCREEN_ADD_TRANSACTION)
    }

    fun handleDrawerItemSelection(itemId: Int) {
        val screenKey = when (itemId) {
            R.id.nav_dashboard -> Constants.SCREEN_DASHBOARD
            R.id.nav_records -> Constants.SCREEN_RECORDS
            R.id.nav_charts -> Constants.SCREEN_CHARTS
            R.id.nav_settings -> Constants.SCREEN_SETTINGS
            else -> ""
        }
        router.navigateTo(screenKey)
    }
}
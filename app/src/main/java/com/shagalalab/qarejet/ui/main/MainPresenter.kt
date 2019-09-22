package com.shagalalab.qarejet.ui.main

import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.util.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) {

    fun openNewTransactionScreen() {
        router.navigateTo(Screens.SCREEN_ADD_TRANSACTION)
    }

    fun openDashboardScreen() {
        router.newRootScreen(Screens.SCREEN_DASHBOARD)
    }

    fun handleDrawerItemSelection(itemId: Int) {
        val screen = when (itemId) {
            R.id.nav_dashboard -> Screens.SCREEN_DASHBOARD
            R.id.nav_records -> Screens.SCREEN_RECORDS
            R.id.nav_charts -> Screens.SCREEN_CHARTS
            R.id.nav_settings -> Screens.SCREEN_SETTINGS
            else -> Screens.SCREEN_EMPTY
        }
        router.navigateTo(screen)
    }
}
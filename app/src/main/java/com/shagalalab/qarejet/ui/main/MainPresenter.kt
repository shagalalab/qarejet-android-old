package com.shagalalab.qarejet.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * Created by atabek on 12/11/2017.
 */

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun openNewTransactionScreen() {
        viewState.addNewTransaction()
    }

}
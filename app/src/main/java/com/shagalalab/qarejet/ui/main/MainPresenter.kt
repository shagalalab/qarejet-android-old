package com.shagalalab.qarejet.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun openNewTransactionScreen() {
        viewState.addNewTransaction()
    }
}
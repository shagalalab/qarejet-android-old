package com.shagalalab.qarejet.ui.splash

import com.arellomobile.mvp.MvpView

interface SplashView : MvpView {
    fun goToNextScreen()
    fun showError(error: String)
}
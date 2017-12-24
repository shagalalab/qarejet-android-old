package com.shagalalab.qarejet.di.component

import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.di.module.*
import com.shagalalab.qarejet.ui.splash.SplashActivity
import com.shagalalab.qarejet.ui.transaction.NewTransactionActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by atabek on 12/15/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, PresenterModule::class, UseCaseModule::class, RepositoryModule::class, DataModule::class))
interface AppComponent {
    fun inject(app: QarejetApp)
    fun inject(activity: SplashActivity)
    fun inject(activity: NewTransactionActivity)

}
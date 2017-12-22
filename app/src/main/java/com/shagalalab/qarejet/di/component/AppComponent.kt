package com.shagalalab.qarejet.di.component

import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.di.module.AppModule
import com.shagalalab.qarejet.di.module.PresenterModule
import com.shagalalab.qarejet.di.module.RepositoryModule
import com.shagalalab.qarejet.di.module.UseCaseModule
import com.shagalalab.qarejet.presentation.newtransaction.NewTransactionActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by atabek on 12/15/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, PresenterModule::class, UseCaseModule::class, RepositoryModule::class))
interface AppComponent {
    fun inject(app: QarejetApp)
    fun inject(activity: NewTransactionActivity)

}
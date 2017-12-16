package com.shagalalab.qarejet.di.component

import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.di.module.*
import com.shagalalab.qarejet.presentation.newtransaction.NewTransactionActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by atabek on 12/15/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, PresenterModule::class, UseCaseModule::class, RepositoryModule::class, DbModule::class))
interface AppComponent {
    fun inject(app: QarejetApp)
    fun inject(activity: NewTransactionActivity)

}
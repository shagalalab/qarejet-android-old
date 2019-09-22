package com.shagalalab.qarejet.di.component

import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.di.module.AppModule
import com.shagalalab.qarejet.di.module.DataModule
import com.shagalalab.qarejet.di.module.PresenterModule
import com.shagalalab.qarejet.di.module.RepositoryModule
import com.shagalalab.qarejet.di.module.UseCaseModule
import com.shagalalab.qarejet.ui.category.CategoryActivity
import com.shagalalab.qarejet.ui.chart.ChartsFragment
import com.shagalalab.qarejet.ui.dashboard.DashboardFragment
import com.shagalalab.qarejet.ui.main.MainActivity
import com.shagalalab.qarejet.ui.record.RecordsFragment
import com.shagalalab.qarejet.ui.splash.SplashActivity
import com.shagalalab.qarejet.ui.transaction.AddTransactionActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, UseCaseModule::class, RepositoryModule::class, DataModule::class])
interface AppComponent {
    fun inject(app: QarejetApp)
    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: AddTransactionActivity)
    fun inject(fragment: RecordsFragment)
    fun inject(fragment: ChartsFragment)
    fun inject(fragment: CategoryActivity)
    fun inject(fragment: DashboardFragment)
}
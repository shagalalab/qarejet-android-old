package com.shagalalab.qarejet

import android.app.Application
import com.shagalalab.qarejet.di.component.AppComponent
import com.shagalalab.qarejet.di.component.DaggerAppComponent
import com.shagalalab.qarejet.di.module.AppModule
import net.danlew.android.joda.JodaTimeAndroid
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class QarejetApp : Application() {
    private lateinit var cicerone: Cicerone<Router>

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        JodaTimeAndroid.init(this)
        cicerone = Cicerone.create()
    }

    fun getNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder

    fun getRouter(): Router = cicerone.router
}
package com.shagalalab.qarejet

import android.app.Application
import com.shagalalab.qarejet.di.component.AppComponent
import com.shagalalab.qarejet.di.component.DaggerAppComponent
import com.shagalalab.qarejet.di.module.AppModule
import net.danlew.android.joda.JodaTimeAndroid

class QarejetApp : Application() {

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
    }
}
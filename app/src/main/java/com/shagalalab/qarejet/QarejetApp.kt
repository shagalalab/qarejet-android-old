package com.shagalalab.qarejet

import android.app.Application
import com.shagalalab.qarejet.di.component.AppComponent
import com.shagalalab.qarejet.di.component.DaggerAppComponent
import com.shagalalab.qarejet.di.module.AppModule

/**
 * Created by atabek on 12/15/2017.
 */

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
    }
}
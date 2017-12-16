package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.QarejetApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by atabek on 12/15/2017.
 */

@Module
class AppModule(val app: QarejetApp) {

    @Provides
    @Singleton
    fun provideApp() = app

}
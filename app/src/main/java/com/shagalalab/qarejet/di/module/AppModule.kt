package com.shagalalab.qarejet.di.module

import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
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
    fun providesApp() = app

    @Provides
    @Singleton
    fun providesContext() = app.applicationContext

    @Provides
    @Singleton
    fun providesAccounts() = arrayOf(Account(1, "Cash"))

    @Provides
    @Singleton
    fun providesCategories() = arrayOf(Category(1, "Food"), Category(2, "Transportation"), Category(3, "Education"))


}
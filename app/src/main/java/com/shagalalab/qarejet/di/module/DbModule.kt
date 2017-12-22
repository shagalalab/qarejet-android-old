package com.shagalalab.qarejet.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by atabek on 12/15/2017.
 */

@Module
class DbModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context) = Room.databaseBuilder(context, Database::class.java, "qarejetdb").build()

    @Provides
    @Singleton
    fun providesAccounts() = arrayOf(Account(1, "Cash"))

    @Provides
    @Singleton
    fun providesCategories() = arrayOf(Category(1, "Food"), Category(2, "Transportation"), Category(3, "Education"))
}
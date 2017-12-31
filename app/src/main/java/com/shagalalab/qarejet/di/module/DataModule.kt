package com.shagalalab.qarejet.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.shagalalab.qarejet.data.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context) = Room.databaseBuilder(context, Database::class.java, "qarejetdb").build()

    @Provides
    @Singleton
    fun providesSharedPrefs(context: Context) = context.getSharedPreferences("QarejetPrefs", Context.MODE_PRIVATE)
}
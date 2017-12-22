package com.shagalalab.qarejet.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.shagalalab.qarejet.data.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by atabek on 12/15/2017.
 */

@Module
class DbModule(val context: Context) {

    @Provides
    @Singleton
    fun providesDatabase() = Room.databaseBuilder(context, Database::class.java, "qarejetdb").build()

}
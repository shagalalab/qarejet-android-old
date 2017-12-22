package com.shagalalab.qarejet.data.db

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by atabek on 12/21/2017.
 */

class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?) = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?) = date?.time
}
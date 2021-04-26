package com.ghataa.metaweather.data.source.local

import androidx.room.TypeConverter
import java.util.Date

/** This class contains [TypeConverter] methods for the local Room database. */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}

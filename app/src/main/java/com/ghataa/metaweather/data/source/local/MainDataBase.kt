package com.ghataa.metaweather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ghataa.metaweather.data.model.WeatherInfo

@Database(entities = [WeatherInfo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MainDataBase : RoomDatabase() {

    abstract fun weatherInfoListDao(): WeatherInfoListDao
}

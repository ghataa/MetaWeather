package com.ghataa.metaweather.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghataa.metaweather.data.model.WeatherInfo

@Dao
interface WeatherInfoListDao {

    @Query("SELECT * FROM weather_info_list")
    suspend fun getWeatherInfoList(): List<WeatherInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(weatherInfo: WeatherInfo)

    @Query("DELETE FROM weather_info_list")
    suspend fun deleteWeatherInfoList()
}

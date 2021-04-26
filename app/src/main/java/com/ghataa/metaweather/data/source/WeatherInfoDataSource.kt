package com.ghataa.metaweather.data.source

import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.model.WeatherInfo

/** Wraps the different data sources.
 * Contains methods for serving, saving, deleting, etc. data from/to the given data source. */
interface WeatherInfoDataSource {

    suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>>

    suspend fun saveWeatherInfo(weatherInfo: WeatherInfo): Result<Unit> {
        return Result.Success(Unit) // default implementation; this method is only for the local data source
    }

    suspend fun deleteWeatherInfoList(): Result<Unit> {
        return Result.Success(Unit) // default implementation; this method is only for the local data source
    }
}

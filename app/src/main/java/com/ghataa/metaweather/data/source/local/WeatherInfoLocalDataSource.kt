package com.ghataa.metaweather.data.source.local

import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.Result.Error
import com.ghataa.metaweather.data.Result.Success
import com.ghataa.metaweather.data.model.WeatherInfo
import com.ghataa.metaweather.data.source.WeatherInfoDataSource

/** Wraps the local Room database as a unified data source. */
class WeatherInfoLocalDataSource internal constructor(
    private val dao: WeatherInfoListDao
) : WeatherInfoDataSource {

    override suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>> =
        try {
            Success(dao.getWeatherInfoList())
        } catch (cause: Throwable) {
            Error(Exception(cause))
        }

    override suspend fun saveWeatherInfo(weatherInfo: WeatherInfo): Result<Unit> =
        try {
            Success(dao.insertWeatherInfo(weatherInfo))
        } catch (cause: Throwable) {
            Error(Exception(cause))
        }

    override suspend fun deleteWeatherInfoList(): Result<Unit> =
        try {
            Success(dao.deleteWeatherInfoList())
        } catch (cause: Throwable) {
            Error(Exception(cause))
        }
}

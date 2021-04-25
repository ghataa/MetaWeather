package com.ghataa.metaweather.data.source.remote

import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.Result.Error
import com.ghataa.metaweather.data.Result.Success
import com.ghataa.metaweather.data.model.WeatherInfo
import com.ghataa.metaweather.data.source.WeatherInfoDataSource

object WeatherInfoRemoteDataSource : WeatherInfoDataSource {

    private val webservice = MainWebservice.create()

    override suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>> =
        try {
            Success(webservice.getWeatherInfoList(month, day))
        } catch (cause: Throwable) {
            Error(Exception(cause))
        }
}

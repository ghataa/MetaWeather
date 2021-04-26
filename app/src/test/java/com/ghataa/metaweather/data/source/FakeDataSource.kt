package com.ghataa.metaweather.data.source

import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.Result.Error
import com.ghataa.metaweather.data.Result.Success
import com.ghataa.metaweather.data.model.WeatherInfo

/** Fake implementation of [WeatherInfoDataSource] for tests. */
class FakeDataSource(var weatherInfoList: MutableList<WeatherInfo>? = mutableListOf()) : WeatherInfoDataSource {

    override suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>> {
        weatherInfoList?.let { return Success(it) }
        return Error(Exception("WeatherInfo not found"))
    }

    override suspend fun saveWeatherInfo(weatherInfo: WeatherInfo): Result<Unit> {
        weatherInfoList?.add(weatherInfo)
        return Success(Unit)
    }

    override suspend fun deleteWeatherInfoList(): Result<Unit> {
        weatherInfoList = mutableListOf()
        return Success(Unit)
    }
}

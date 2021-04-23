package com.ghataa.metaweather.data.source

import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.model.WeatherInfo

interface WeatherInfoDataSource {

    suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>>
}

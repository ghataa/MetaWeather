package com.ghataa.metaweather.data.source

import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.model.WeatherInfo

/** The Repository layer which serves the [ViewModel] layer and also wraps the different data sources. */
interface WeatherInfoRepository {

    suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>>

    fun invalidateCache()
}

package com.ghataa.metaweather.data.source

import android.util.Log
import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.Result.Error
import com.ghataa.metaweather.data.Result.Success
import com.ghataa.metaweather.data.model.WeatherInfo
import com.ghataa.metaweather.di.ApplicationModule.WeatherInfoRemoteDataSource
import javax.inject.Inject

class DefaultWeatherInfoRepository @Inject constructor(
    @WeatherInfoRemoteDataSource private val weatherInfoRemoteDataSource: WeatherInfoDataSource
) : WeatherInfoRepository {

    override suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>> =
        fetchWeatherInfoFromRemoteOrLocal(month, day)

    private suspend fun fetchWeatherInfoFromRemoteOrLocal(month: Int, day: Int): Result<List<WeatherInfo>> {
        val remoteWeatherInfoList = weatherInfoRemoteDataSource.getWeatherInfoList(month, day)

        when (remoteWeatherInfoList) {
            is Error -> Log.e("Error", "Remote data source fetch failed for WeatherInfoList")
            is Success -> return remoteWeatherInfoList
            else -> throw IllegalStateException()
        }

        return Error(Exception(remoteWeatherInfoList.toString()))
    }
}

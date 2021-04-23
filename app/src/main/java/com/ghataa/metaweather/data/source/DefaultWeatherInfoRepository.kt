package com.ghataa.metaweather.data.source

import android.util.Log
import com.ghataa.metaweather.data.Result
import com.ghataa.metaweather.data.Result.Error
import com.ghataa.metaweather.data.Result.Success
import com.ghataa.metaweather.data.model.WeatherInfo
import com.ghataa.metaweather.di.ApplicationModule.WeatherInfoLocalDataSource
import com.ghataa.metaweather.di.ApplicationModule.WeatherInfoRemoteDataSource
import javax.inject.Inject

class DefaultWeatherInfoRepository @Inject constructor(
    @WeatherInfoRemoteDataSource private val remoteWeatherInfoDataSource: WeatherInfoDataSource,
    @WeatherInfoLocalDataSource private val localWeatherInfoDataSource: WeatherInfoDataSource
) : WeatherInfoRepository {

    override suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>> =
        fetchWeatherInfoFromRemoteOrLocal(month, day)

    private suspend fun fetchWeatherInfoFromRemoteOrLocal(month: Int, day: Int): Result<List<WeatherInfo>> {
        val remoteWeatherInfoList = remoteWeatherInfoDataSource.getWeatherInfoList(month, day)

        // Try to return from Remote
        when (remoteWeatherInfoList) {
            is Error -> Log.e("Error", "Remote data source fetch failed for WeatherInfoList")
            is Success -> {
                refreshLocalDataSource(remoteWeatherInfoList.data)
                return remoteWeatherInfoList
            }
            else -> throw IllegalStateException()
        }

        // Remote failed, so try to return from Local
        val localWeatherInfoList = localWeatherInfoDataSource.getWeatherInfoList(month, day)

        if (localWeatherInfoList is Success)
            return localWeatherInfoList

        return Error(Exception("Error fetching WeatherInfoList from remote and local"))
    }

    private suspend fun refreshLocalDataSource(weatherInfoList: List<WeatherInfo>) {
        localWeatherInfoDataSource.deleteWeatherInfoList()
        for (weatherInfo in weatherInfoList) {
            localWeatherInfoDataSource.saveWeatherInfo(weatherInfo)
        }
    }
}

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

    private var cacheRefreshTimestamp: Long = 0

    override suspend fun getWeatherInfoList(month: Int, day: Int): Result<List<WeatherInfo>> {
        if (cacheIsInvalid()) fetchFromRemoteAndSaveToLocal(month, day)
        return fetchWeatherInfoFromLocal(month, day)
    }

    override fun invalidateCache() {
        cacheRefreshTimestamp = 0
    }

    private fun cacheIsInvalid(): Boolean =
        System.currentTimeMillis() - cacheRefreshTimestamp > ONE_MINUTE_MS

    private suspend fun fetchFromRemoteAndSaveToLocal(month: Int, day: Int) {
        val remoteWeatherInfoList = remoteWeatherInfoDataSource.getWeatherInfoList(month, day)

        when (remoteWeatherInfoList) {
            is Error -> Log.e("Error", "Remote data source fetch failed for WeatherInfoList")
            is Success -> {
                refreshLocalDataSource(remoteWeatherInfoList.data)
                cacheRefreshTimestamp = System.currentTimeMillis()
            }
            else -> throw IllegalStateException()
        }
    }

    private suspend fun fetchWeatherInfoFromLocal(month: Int, day: Int): Result<List<WeatherInfo>> {
        val localWeatherInfoList = localWeatherInfoDataSource.getWeatherInfoList(month, day)

        if (localWeatherInfoList is Success)
            return localWeatherInfoList

        return Error(Exception("Error fetching WeatherInfoList from local"))
    }

    private suspend fun refreshLocalDataSource(weatherInfoList: List<WeatherInfo>) {
        localWeatherInfoDataSource.deleteWeatherInfoList()
        for (weatherInfo in weatherInfoList) {
            localWeatherInfoDataSource.saveWeatherInfo(weatherInfo)
        }
    }

    companion object {
        private const val ONE_MINUTE_MS = 60_000L
    }
}

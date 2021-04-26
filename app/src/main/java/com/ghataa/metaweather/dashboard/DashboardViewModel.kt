package com.ghataa.metaweather.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghataa.metaweather.data.Result.Success
import com.ghataa.metaweather.data.model.WeatherInfo
import com.ghataa.metaweather.data.source.WeatherInfoRepository
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import kotlin.collections.ArrayList

/** This [ViewModel] class is an intermediator between the Repository and View layers. */
class DashboardViewModel @Inject constructor(
    private val weatherInfoRepository: WeatherInfoRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<WeatherInfo>>().apply { value = emptyList() }
    val items: LiveData<List<WeatherInfo>> = _items

    private val _refreshingWeatherInfoList = MutableLiveData<Boolean>()
    val refreshingWeatherInfoList: LiveData<Boolean> = _refreshingWeatherInfoList

    /** Loads the current weather information from the Repository layer.
     * @param invalidateCache If true, local cache will be updated by information from the remote data source. */
    fun loadWeatherInfo(invalidateCache: Boolean) {
        if (invalidateCache) weatherInfoRepository.invalidateCache()

        _refreshingWeatherInfoList.value = true

        viewModelScope.launch {
            val result = weatherInfoRepository.getWeatherInfoList(
                Calendar.getInstance().get(Calendar.MONTH) + 1, // because it starts from 0
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )

            _items.value =
                if (result is Success) ArrayList(result.data).sortedBy { it.created }.reversed() else emptyList()

            _refreshingWeatherInfoList.value = false
        }
    }

    /** Invalidates the local cache and loads the current weather information from the Repository layer. */
    fun refresh() = loadWeatherInfo(true)
}

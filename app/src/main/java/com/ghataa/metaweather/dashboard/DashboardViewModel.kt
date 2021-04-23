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

class DashboardViewModel @Inject constructor(
    private val weatherInfoRepository: WeatherInfoRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<WeatherInfo>>().apply { value = emptyList() }
    val items: LiveData<List<WeatherInfo>> = _items

    fun loadWeatherInfo() {
        viewModelScope.launch {
            val result = weatherInfoRepository.getWeatherInfoList(
                Calendar.getInstance().get(Calendar.MONTH) + 1, // starts from 0
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )

            _items.value = if (result is Success) ArrayList(result.data) else emptyList()
        }
    }
}

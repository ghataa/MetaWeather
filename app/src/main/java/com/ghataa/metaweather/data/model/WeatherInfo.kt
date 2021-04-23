package com.ghataa.metaweather.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class WeatherInfo(
    @SerializedName("id") val id: Long,
    @SerializedName("applicable_date") val applicableDate: Date,
    @SerializedName("weather_state_name") val weatherStateName: String,
    @SerializedName("weather_state_abbr") val weatherStateAbbr: String,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_direction") val windDirection: Double,
    @SerializedName("wind_direction_compass") val windDirectionCompass: String,
    @SerializedName("min_temp") val minTemp: Double,
    @SerializedName("max_temp") val maxTemp: Double,
    @SerializedName("the_temp") val currentTemp: Double,
    @SerializedName("air_pressure") val airPressure: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("visibility") val visibility: Double,
    @SerializedName("predictability") val predictability: Int
)

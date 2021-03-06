package com.ghataa.metaweather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Date
import java.util.UUID
import kotlin.random.Random

@Entity(tableName = "weather_info_list")
data class WeatherInfo(
    @PrimaryKey @SerializedName("id") val id: Long,
    @SerializedName("applicable_date") val applicableDate: Date,
    @SerializedName("weather_state_name") val weatherStateName: String,
    @SerializedName("weather_state_abbr") val weatherStateAbbr: String,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_direction") val windDirection: Double,
    @SerializedName("wind_direction_compass") val windDirectionCompass: String,
    @SerializedName("created") val created: Date,
    @SerializedName("min_temp") val minTemp: Double,
    @SerializedName("max_temp") val maxTemp: Double,
    @SerializedName("the_temp") val currentTemp: Double,
    @SerializedName("air_pressure") val airPressure: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("visibility") val visibility: Double,
    @SerializedName("predictability") val predictability: Int
)

/** Instantiates a dummy test double object for [WeatherInfo].
 * @return The dummy test double object. */
fun getDummyWeatherInfo(): WeatherInfo =
    WeatherInfo(
        UUID.randomUUID().mostSignificantBits,
        Date(),
        listOf("Snow", "Sleet", "Hail", "Thunderstorm", "Heavy Rain", "Light Rain", "Showers", "Heavy Cloud").random(),
        listOf("sn", "sl", "h", "t", "hr", "lr", "s", "hc").random(),
        Random.nextDouble(),
        Random.nextDouble(),
        listOf("NW", "WNW", "NNW").random(),
        Date(),
        Random.nextDouble(),
        Random.nextDouble(),
        Random.nextDouble(),
        Random.nextDouble(),
        Random.nextDouble(),
        Random.nextDouble(),
        Random.nextInt()
    )

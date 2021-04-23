package com.ghataa.metaweather.data.source.remote

import com.ghataa.metaweather.data.model.WeatherInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Path

interface MainWebservice {

    @POST("location/804365/2021/{month}/{day}")
    suspend fun getWeatherInfoList(
        @Path("month") month: Int,
        @Path("day") day: Int
    ): List<WeatherInfo>

    companion object Factory {
        private const val BASE_URL = "https://www.metaweather.com/api/"

        fun create(): MainWebservice {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(MainWebservice::class.java)
        }
    }
}

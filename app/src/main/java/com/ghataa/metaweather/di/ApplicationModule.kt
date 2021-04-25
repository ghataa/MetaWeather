package com.ghataa.metaweather.di

import android.content.Context
import androidx.room.Room
import com.ghataa.metaweather.data.source.DefaultWeatherInfoRepository
import com.ghataa.metaweather.data.source.WeatherInfoDataSource
import com.ghataa.metaweather.data.source.WeatherInfoRepository
import com.ghataa.metaweather.data.source.local.MainDataBase
import com.ghataa.metaweather.data.source.local.WeatherInfoLocalDataSource
import com.ghataa.metaweather.data.source.remote.WeatherInfoRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(RUNTIME)
    annotation class WeatherInfoRemoteDataSource

    @Qualifier
    @Retention(RUNTIME)
    annotation class WeatherInfoLocalDataSource

    @JvmStatic
    @Singleton
    @WeatherInfoRemoteDataSource
    @Provides
    fun provideWeatherInfoRemoteDataSource(): WeatherInfoDataSource = WeatherInfoRemoteDataSource

    @JvmStatic
    @Singleton
    @WeatherInfoLocalDataSource
    @Provides
    fun provideWeatherInfoLocalDataSource(database: MainDataBase): WeatherInfoDataSource =
        WeatherInfoLocalDataSource(database.weatherInfoListDao())

    @JvmStatic
    @Singleton
    @Provides
    fun provideMainDataBase(context: Context): MainDataBase =
        Room.databaseBuilder(
            context.applicationContext,
            MainDataBase::class.java,
            "Main.db"
        ).build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultWeatherInfoRepository): WeatherInfoRepository
}

package com.ghataa.metaweather.di

import com.ghataa.metaweather.data.source.DefaultWeatherInfoRepository
import com.ghataa.metaweather.data.source.WeatherInfoDataSource
import com.ghataa.metaweather.data.source.WeatherInfoRepository
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
    fun provideWeatherInfoRemoteDataSource(): WeatherInfoDataSource {
        return WeatherInfoRemoteDataSource
    }

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

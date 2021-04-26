package com.ghataa.metaweather.data.source

import com.ghataa.metaweather.data.Result.Error
import com.ghataa.metaweather.data.Result.Success
import com.ghataa.metaweather.data.model.getDummyWeatherInfo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultWeatherInfoRepositoryTest {

    private val weatherInfo1 = getDummyWeatherInfo()
    private val weatherInfo2 = getDummyWeatherInfo()
    private val weatherInfo3 = getDummyWeatherInfo()
    private val remoteWeatherInfoList = listOf(weatherInfo1, weatherInfo2)
    private val localWeatherInfoList = listOf(weatherInfo3)
    private lateinit var remoteWeatherInfoDataSource: FakeDataSource
    private lateinit var localWeatherInfoDataSource: FakeDataSource

    private lateinit var defaultWeatherInfoRepository: DefaultWeatherInfoRepository

    @Before
    fun createRepository() {
        remoteWeatherInfoDataSource = FakeDataSource(remoteWeatherInfoList.toMutableList())
        localWeatherInfoDataSource = FakeDataSource(localWeatherInfoList.toMutableList())
        defaultWeatherInfoRepository = DefaultWeatherInfoRepository(remoteWeatherInfoDataSource, localWeatherInfoDataSource)
    }

    @Test
    fun whenGetWeatherInfoList_withEmptyRepository_succeeds() = runBlockingTest {
        val emptySource = FakeDataSource()
        val weatherInfoRepository = DefaultWeatherInfoRepository(emptySource, emptySource)

        assertThat(weatherInfoRepository.getWeatherInfoList(1, 1) is Success).isTrue()
    }

    @Test
    fun whenGetWeatherInfoList_withNullList_fails() = runBlockingTest {
        val emptySource = FakeDataSource(null)
        val weatherInfoRepository = DefaultWeatherInfoRepository(emptySource, emptySource)

        assertThat(weatherInfoRepository.getWeatherInfoList(1, 1) is Error).isTrue()
    }

    @Test
    fun whenGetWeatherInfoList_thenRetrievedFromRemoteDataSource() = runBlockingTest {
        val weatherInfoList = defaultWeatherInfoRepository.getWeatherInfoList(1, 1) as Success

        assertThat(weatherInfoList.data).isEqualTo(remoteWeatherInfoList)
    }

    @Test
    fun whenGetWeatherInfoList_withRemoteDataSourceUnavailable_thenRetrievedFromLocal() = runBlockingTest {
        remoteWeatherInfoDataSource.weatherInfoList = null

        assertThat((defaultWeatherInfoRepository.getWeatherInfoList(1, 1) as Success).data)
            .isEqualTo(localWeatherInfoList)
    }

    @Test
    fun whenGetWeatherInfoList_WithBothDataSourcesUnavailable_returnsError() = runBlockingTest {
        remoteWeatherInfoDataSource.weatherInfoList = null
        localWeatherInfoDataSource.weatherInfoList = null

        assertThat(defaultWeatherInfoRepository.getWeatherInfoList(1, 1)).isInstanceOf(Error::class.java)
    }

    @Test
    fun whenGetWeatherInfoList_localDataSourceRefreshes() = runBlockingTest {
        val initialLocal = localWeatherInfoDataSource.weatherInfoList!!.toList()
        val newWeatherInfoList = (defaultWeatherInfoRepository.getWeatherInfoList(1, 1) as Success).data // from remote

        assertThat(newWeatherInfoList).isEqualTo(remoteWeatherInfoList)
        assertThat(newWeatherInfoList).isEqualTo(localWeatherInfoDataSource.weatherInfoList)
        assertThat(localWeatherInfoDataSource.weatherInfoList).isNotEqualTo(initialLocal)
    }
}

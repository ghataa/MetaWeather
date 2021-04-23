package com.ghataa.metaweather.di

import androidx.lifecycle.ViewModel
import com.ghataa.metaweather.dashboard.DashboardFragment
import com.ghataa.metaweather.dashboard.DashboardViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class WeatherInfoModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun dashboardFragment(): DashboardFragment

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindViewModel(viewModel: DashboardViewModel): ViewModel
}

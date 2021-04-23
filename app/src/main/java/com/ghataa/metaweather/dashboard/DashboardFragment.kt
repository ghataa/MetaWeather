package com.ghataa.metaweather.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ghataa.metaweather.databinding.FragmentDashboardBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DashboardFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DashboardViewModel> { viewModelFactory }

    private lateinit var viewBinding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDashboardBinding.inflate(inflater, container, false)

        viewBinding.loadWeatherInfoButton.setOnClickListener {
            viewModel.loadWeatherInfo()
        }

        return viewBinding.root
    }
}

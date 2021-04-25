package com.ghataa.metaweather.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        initViewBinding(inflater, container)
        initSwipeToRefresh()
        return viewBinding.root
    }

    private fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewBinding = FragmentDashboardBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        viewBinding.loadWeatherInfoButton.setOnClickListener {
            viewModel.loadWeatherInfo()
        }
    }

    private fun initSwipeToRefresh() {
        val refreshObserver = Observer<Boolean> { isRefreshing ->
            viewBinding.swipeRefreshLayout.isRefreshing = isRefreshing
        }

        viewModel.refreshingWeatherInfoList.observe(viewLifecycleOwner, refreshObserver)
    }
}

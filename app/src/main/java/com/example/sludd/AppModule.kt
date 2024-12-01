package com.example.sludd

import com.example.sludd.data.WeatherRepository
import com.example.sludd.network.WeatherApiClient
import com.example.sludd.ui.screens.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { WeatherApiClient.apiService }
    single { WeatherRepository(apiService = get()) }
    viewModel { WeatherViewModel(weatherRepository = get()) }
}
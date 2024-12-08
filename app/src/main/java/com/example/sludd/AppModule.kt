package com.example.sludd

import android.location.Geocoder
import com.example.sludd.data.WeatherRepository
import com.example.sludd.location.GeocoderService
import com.example.sludd.location.LocationProvider
import com.example.sludd.network.WeatherApiClient
import com.example.sludd.ui.screens.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { WeatherApiClient.apiService }
    single { WeatherRepository(apiService = get()) }
    single { LocationProvider(context = get()) }
    single { Geocoder(get()) }
    single { GeocoderService(geocoder = get()) }
    viewModel { WeatherViewModel(weatherRepository = get(), locationProvider = get(), geocoderService = get()) }
}
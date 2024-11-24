package com.example.sludd.data

import com.example.sludd.network.ServiceFactory
import com.example.sludd.network.WeatherAPiService
import com.example.sludd.ui.screens.WeatherViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<WeatherAPiService> { ServiceFactory.createWeatherAPiService() }
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    viewModel { WeatherViewModel(get())}
}
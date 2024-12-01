package com.example.sludd.data

import com.example.sludd.network.WeatherApiService

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double) =
        apiService.getCurrentWeather(latitude, longitude)
}

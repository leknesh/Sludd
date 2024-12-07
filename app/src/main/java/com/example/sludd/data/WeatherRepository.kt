package com.example.sludd.data

import com.example.sludd.network.WeatherApiService

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getWeather(latitude: Double, longitude: Double) =
        apiService.getWeather(latitude, longitude).toWeather()
}

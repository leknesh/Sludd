package com.example.sludd.network

import com.example.sludd.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


const val CURRENT_PARAMS = "temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m"
const val FORECAST_PARAMS = "weather_code,temperature_2m_max,temperature_2m_min"

interface WeatherApiService {
    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("timezone") timezone: String = "Europe/Berlin",
        @Query("current") current: String = CURRENT_PARAMS,
        @Query("daily") daily: String = FORECAST_PARAMS,
    ) : WeatherResponse
}

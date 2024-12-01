package com.example.sludd.network

import com.example.sludd.data.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

//forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m
const val CURRENT_PARAMS = "temperature_2m,weather_code,relative_humidity_2m,wind_speed_10m"
//const val FORECAST_PARAMS = "temperature_2m,weather_code,relative_humidity_2m,wind_speed_10m"

interface OpenWeatherService {
    @GET("forecast?current_weather=true")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = CURRENT_PARAMS,
//        @Query("hourly") hourly: String = FORECAST_PARAMS
    ) : CurrentWeatherResponse
}

package com.example.sludd.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.open-meteo.com/v1/"

object WeatherApiManager {
    private val service: OpenWeatherService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(OpenWeatherService::class.java)
    }
}
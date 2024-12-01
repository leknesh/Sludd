package com.example.sludd.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.open-meteo.com/v1/"

object WeatherApiManager {

    private val retrofit: Retrofit by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val weatherService: OpenWeatherService by lazy {
        retrofit.create(OpenWeatherService::class.java)
    }

    fun getInstance(): OpenWeatherService = weatherService

    suspend fun getCurrentWeather(latitude: Double, longitude: Double) =
        weatherService.getCurrentWeather(latitude, longitude)
}
package com.example.sludd.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://api.example.com/"

interface WeatherAPiService {
    @GET("current")
    suspend fun getCurrentWeather(): Weather
}

object ServiceFactory {
    private const val BASE_URL = "https://api.example.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    fun createWeatherAPiService(): WeatherAPiService {
        return retrofit.create(WeatherAPiService::class.java)
    }
}
package com.example.sludd.network

data class WeatherInfo(
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double
)

fun extractCurrentWeatherInfo(response: MetWeatherResponse): WeatherInfo? {
    val currentTimeSeries = response.properties.timeseries.firstOrNull() ?: return null
    val details = currentTimeSeries.data.instant.details
    return WeatherInfo(
        temperature = details.airTemp.toDouble(),
        humidity = details.relativeHumidity.toDouble(),
        windSpeed = details.windSpeed.toDouble()
    )
}
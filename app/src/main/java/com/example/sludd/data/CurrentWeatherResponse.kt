package com.example.sludd.data

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("temperature_2m") val temp: Double,
    @SerializedName("relative_humidity_2m") val humidity: Int,
    @SerializedName("weather_code") val weatherCode: Int,
    @SerializedName("wind_speed_10m") val windSpeed: Double
)

data class CurrentWeather(
    val temp: Double,
    val humidity: Int,
    val windSpeed: Double,
    val description: String,
)

fun CurrentWeather.fromResponse(response: CurrentWeatherResponse): CurrentWeather {
    return CurrentWeather(
        temp = response.temp,
        humidity = response.humidity,
        windSpeed = response.windSpeed,
        description = WeatherCode.fromCode(response.weatherCode).description
    )
}

// response example:
//  {
//    "latitude": 52.52,
//    "longitude": 13.419998,
//    "generationtime_ms": 0.0560283660888672,
//    "utc_offset_seconds": 0,
//    "timezone": "GMT",
//    "timezone_abbreviation": "GMT",
//    "elevation": 38,
//    "current_units": {
//    "time": "iso8601",
//    "interval": "seconds",
//    "temperature_2m": "°C",
//    "relative_humidity_2m": "%",
//    "weather_code": "wmo code",
//    "wind_speed_10m": "km/h"
//   },
//    "current": {
//    "time": "2024-12-01T09:30",
//    "interval": 900,
//    "temperature_2m": -0.1,
//    "relative_humidity_2m": 85,
//    "weather_code": 0,
//    "wind_speed_10m": 6.5
//}

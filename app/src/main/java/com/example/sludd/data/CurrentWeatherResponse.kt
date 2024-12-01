package com.example.sludd.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("generationtime_ms") val generationTimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerializedName("current_units") val currentWeatherUnits: WeatherUnitsResponse,
    @SerializedName("current") val currentWeather: CurrentWeatherResponse
)

data class WeatherUnitsResponse(
    val time: String,
    val interval: String,
//    val temperature: String,
//    val windspeed: String,
//    val winddirection: String,
//    val weatherCodeUnit : String
    @SerializedName("temperature_2m") val temperatureUnit: String,
    @SerializedName("relative_humidity_2m") val humidityUnit : String,
    @SerializedName("wind_speed_10m") val windSpeedUnit: String,
    @SerializedName("weather_code") val weatherCodeUnit : String
)

data class CurrentWeatherResponse(
    val time: String,
    val interval: Int,
//    val temperature: Double,
//    val windspeed: Double,
//    val winddirection: Int,
//    val weathercode : Int
    @SerializedName("temperature_2m") val temperature: Double,
    @SerializedName("relative_humidity_2m") val humidity : Int,
    @SerializedName("wind_speed_10m") val windSpeed: Double,
    @SerializedName("weather_code") val weatherCode : Int
)

data class CurrentWeather(
    val temperature: String,
    val humidity: String,
    val windSpeed: String,
//    val windDirection: String,
    val description: String,
    val icon: Int
)

fun WeatherResponse.toCurrentWeather(): CurrentWeather {
    val weatherCode = WeatherCode.fromCode(this.currentWeather.weatherCode)
    return CurrentWeather(
        temperature = "${currentWeather.temperature} ${currentWeatherUnits.temperatureUnit}",
        humidity = "${currentWeather.humidity} ${currentWeatherUnits.humidityUnit}",
        windSpeed = "${currentWeather.windSpeed} ${currentWeatherUnits.windSpeedUnit}",
//        windDirection = "${currentWeather.winddirection} ${currentWeatherUnits.winddirection}",
        description = weatherCode.description,
        icon = weatherCode.getDrawable()
    )
}
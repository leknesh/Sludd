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
    @SerializedName("current_weather_units") val currentWeatherUnits: WeatherUnitsResponse,
    @SerializedName("current_weather") val currentWeather: CurrentWeatherResponse
)

data class WeatherUnitsResponse(
    val time: String,
    val interval: String,
    val temperature: String,
    val windspeed: String,
    val winddirection: String,
    val weatherCodeUnit : String
//    @SerializedName("temperature_2m") val temperatureUnit: String,
//    @SerializedName("relative_humidity_2m") val humidityUnit : String,
//    @SerializedName("wind_speed_10m") val windSpeedUnit: String,
//    @SerializedName("weather_code") val weatherCodeUnit : String
)

data class CurrentWeatherResponse(
    val time: String,
    val interval: Int,
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Int,
    val weathercode : Int

//    @SerializedName("temperature_2m") val temperature: Double,
//    @SerializedName("relative_humidity_2m") val humidity : Int,
//    @SerializedName("wind_speed_10m") val windSpeed: Double,
//    @SerializedName("weather_code") val weatherCode : Int
)

data class CurrentWeather(
    val temperature: String,
//    val humidity: String,
    val windSpeed: String,
    val windDirection: String,
    val description: String
)

fun WeatherResponse.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        temperature = "${currentWeather.temperature} ${currentWeatherUnits.temperature}",
//        humidity = "${currentWeather.humidity} ${currentWeatherUnits.humidityUnit}",
        windSpeed = "${currentWeather.windspeed} ${currentWeatherUnits.windspeed}",
        windDirection = "${currentWeather.winddirection} ${currentWeatherUnits.winddirection}",
        description = WeatherCode.fromCode(this.currentWeather.weathercode).description
    )
}

// response example:
//
//{
//    "latitude": 52.52,
//    "longitude": 13.419998,
//    "generationtime_ms": 0.059962272644043,
//    "utc_offset_seconds": 3600,
//    "timezone": "Europe/Berlin",
//    "timezone_abbreviation": "CET",
//    "elevation": 38,
//    "current_units": {
//    "time": "iso8601",
//    "interval": "seconds",
//    "temperature_2m": "°C",
//    "relative_humidity_2m": "%",
//    "weather_code": "wmo code",
//    "wind_speed_10m": "km/h"
//},
//    "current": {
//    "time": "2024-12-01T12:30",
//    "interval": 900,
//    "temperature_2m": 2.1,
//    "relative_humidity_2m": 77,
//    "weather_code": 0,
//    "wind_speed_10m": 6.8
//}
//
//{"latitude":52.52,"longitude":13.419998,"generationtime_ms":0.03600120544433594,"utc_offset_seconds":0,"timezone":"GMT","timezone_abbreviation":"GMT","elevation":38.0,"current_weather_units":{"time":"iso8601","interval":"seconds","temperature":"°C","windspeed":"km/h","winddirection":"°","is_day":"","weathercode":"wmo code"},"current_weather":{"time":"2024-12-01T11:30","interval":900,"temperature":2.1,"windspeed":6.8,"winddirection":155,"is_day":1,"weathercode":0}}
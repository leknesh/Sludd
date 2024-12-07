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
    @SerializedName("current_units") val currentWeatherUnits: CurrentUnitsResponse,
    @SerializedName("current") val currentWeather: CurrentWeatherResponse,
    @SerializedName("daily_units") val dailyUnits: ForecastUnitsResponse,
    val daily: ForecastWeatherResponse
)

data class CurrentUnitsResponse(
    val time: String,
    val interval: String,
    @SerializedName("temperature_2m") val temperatureUnit: String,
    @SerializedName("relative_humidity_2m") val humidityUnit : String,
    @SerializedName("wind_speed_10m") val windSpeedUnit: String,
    @SerializedName("weather_code") val weatherCodeUnit : String
)

data class CurrentWeatherResponse(
    val time: String,
    val interval: Int,
    @SerializedName("temperature_2m") val temperature: Double,
    @SerializedName("relative_humidity_2m") val humidity : Int,
    @SerializedName("wind_speed_10m") val windSpeed: Double,
    @SerializedName("weather_code") val weatherCode : Int
)

data class ForecastUnitsResponse (
    val time: String,
    @SerializedName("weather_code") val weatherCodeUnit : String,
    @SerializedName("temperature_2m_max") val temperatureMaxUnit: String,
    @SerializedName("temperature_2m_min") val temperatureMinUnit : String,
)

data class ForecastWeatherResponse (
    val time: List<String>,
    @SerializedName("weather_code") val weatherCode: List<Int>,
    @SerializedName("temperature_2m_max") val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min") val temperatureMin: List<Double>,
)

data class ForecastWeather(
    val time: String,
    val temperatureMax: String,
    val temperatureMin: String,
    val weatherCode: WeatherCode,
)

data class CurrentWeather(
    val temperature: String,
    val humidity: String,
    val windSpeed: String,
    val weatherCode: WeatherCode,
)

data class WeatherInfo(
    val latitude: Double,
    val longitude: Double,
    val currentWeather: CurrentWeather,
    val foreCast: List<ForecastWeather>,
)

fun WeatherResponse.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        temperature = "${currentWeather.temperature} ${currentWeatherUnits.temperatureUnit}",
        humidity = "${currentWeather.humidity} ${currentWeatherUnits.humidityUnit}",
        windSpeed = "${currentWeather.windSpeed} ${currentWeatherUnits.windSpeedUnit}",
        weatherCode = WeatherCode.fromCode(currentWeather.weatherCode),
    )
}

fun WeatherResponse.toForecastList(): List<ForecastWeather> {
    val forecastList = mutableListOf<ForecastWeather>()
    for (i in this.daily.time.indices) {
        forecastList.add(
            ForecastWeather(
                time = daily.time[i],
                weatherCode = WeatherCode.fromCode(daily.weatherCode[i]),
                temperatureMax = "${daily.temperatureMax[i]} ${dailyUnits.temperatureMaxUnit}",
                temperatureMin = "${daily.temperatureMin[i]} ${dailyUnits.temperatureMinUnit}"
            )
        )
    }
    return forecastList
}

fun WeatherResponse.toWeather(): WeatherInfo {
    return WeatherInfo(
        latitude = latitude,
        longitude = longitude,
        currentWeather = this.toCurrentWeather(),
        foreCast = this.toForecastList()
    )
}
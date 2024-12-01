package com.example.sludd.network

data class MetWeatherResponse(
    val properties: Properties
)

data class Properties(
    val timeseries: List<TimeSeries>
)

data class TimeSeries(
    val time: String,
    val data: Data
)

data class Data(
    val instant: Instant
)

data class Instant(
    val details: Details
)

data class Details(
    val airTemp: Float,
    val relativeHumidity: Float,
    val windSpeed: Float
)

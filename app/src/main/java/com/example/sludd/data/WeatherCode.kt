package com.example.sludd.data

// Sealed class used to allow for extending with other params, like icons
sealed class WeatherCode(val code: Int, val description: String) {
    object ClearSky : WeatherCode(0, "Clear Sky")
    object MainlyClear : WeatherCode(1, "Mainly clear")
    object PartlyCloudy : WeatherCode(2, "Partly cloudy")
    object Overcast : WeatherCode(3, "Overcast")
    object Fog : WeatherCode(45, "Fog")
    object DepositingRimeFog : WeatherCode(48, "Depositing rime fog")
    object DrizzleLight : WeatherCode(51, "Drizzle: Light intensity")
    object DrizzleModerate : WeatherCode(53, "Drizzle: Moderate intensity")
    object DrizzleDense : WeatherCode(55, "Drizzle: Dense intensity")
    object FreezingDrizzle : WeatherCode(56, "Freezing drizzle: Light intensity")
    object FreezingDrizzleDense : WeatherCode(57, "Freezing drizzle: Dense intensity")
    object RainSlight : WeatherCode(61, "Rain: Slight intensity")
    object RainModerate : WeatherCode(63, "Rain: Moderate intensity")
    object RainHeavy : WeatherCode(65, "Rain: Heavy intensity")
    object FreezingRainLight : WeatherCode(66, "Freezing rain: Light intensity")
    object FreezingRainHeavy : WeatherCode(67, "Freezing rain: Heavy intensity")
    object SnowFallSlight : WeatherCode(71, "Snow fall: Slight intensity")
    object SnowFallModerate : WeatherCode(73, "Snow fall: Moderate intensity")
    object SnowFallHeavy : WeatherCode(75, "Snow fall: Heavy intensity")
    object SnowGrains : WeatherCode(77, "Snow grains")
    object RainShowersSlight : WeatherCode(80, "Rain showers: Slight intensity")
    object RainShowersModerate : WeatherCode(81, "Rain showers: Moderate intensity")
    object RainShowersViolent : WeatherCode(82, "Rain showers: Violent intensity")
    object SnowShowersSlight : WeatherCode(85, "Snow showers: Slight intensity")
    object SnowShowersHeavy : WeatherCode(86, "Snow showers: Heavy intensity")
    object ThunderstormSlight : WeatherCode(95, "Thunderstorm: Slight intensity")
    object ThunderstormSlightHail : WeatherCode(96, "Thunderstorm with slight hail")
    object ThunderstormHeavyHail : WeatherCode(99, "Thunderstorm with heavy hail")
    object Unknown : WeatherCode(-1, "Unknown")

    companion object {
        fun fromCode(code: Int): WeatherCode {
            return values().firstOrNull { it.code == code } ?: Unknown
        }

        private fun values() = listOf(
            ClearSky, MainlyClear, PartlyCloudy, Overcast, Fog, DepositingRimeFog, DrizzleLight,
            DrizzleModerate, DrizzleDense, FreezingDrizzle, FreezingDrizzleDense, RainSlight,
            RainModerate, RainHeavy, FreezingRainLight, FreezingRainHeavy, SnowFallSlight,
            SnowFallModerate, SnowFallHeavy, SnowGrains, RainShowersSlight, RainShowersModerate,
            RainShowersViolent, SnowShowersSlight, SnowShowersHeavy, ThunderstormSlight,
            ThunderstormSlightHail, ThunderstormHeavyHail, Unknown
        )
    }
}
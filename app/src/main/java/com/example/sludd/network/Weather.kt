package com.example.sludd.network

class Weather {
    val temperature: Int = 0
    val description: String = ""
    val windStrength: Double = 0.0
    val windDirection: CardinalDirection = CardinalDirection.NORTH
}

enum class CardinalDirection {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST
}
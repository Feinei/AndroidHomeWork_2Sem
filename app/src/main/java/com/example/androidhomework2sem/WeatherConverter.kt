package com.example.androidhomework2sem

import kotlin.math.roundToInt

class WeatherConverter {
    companion object {
        fun fromFtoC(farengeitTemp: Double) = (farengeitTemp - 273.15).roundToInt()

        fun getWindDirection(deg: Int) = when (deg) {
            0 -> "N"
            90 -> "E"
            180 -> "S"
            270 -> "W"
            in 1..89 -> "NE"
            in 91..179 -> "SE"
            in 181..269 -> "SW"
            in 271..359 -> "NW"
            else -> "N"
        }
    }
}
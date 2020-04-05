package com.example.androidhomework2sem.utils

import java.math.BigDecimal
import java.math.RoundingMode

class UtilMethods {

    companion object {

        fun roundDoubleToOneDigit(number: Double) =
            BigDecimal(number).setScale(1, RoundingMode.UP).toDouble()
    }
}

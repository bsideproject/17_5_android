package com.carpick.carpickapp.util

import java.time.LocalDate

object Util {
    fun getDate(): Int {
        return LocalDate.now().toString().replace("-", "").toInt()
    }
}
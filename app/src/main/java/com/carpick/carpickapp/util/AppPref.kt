package com.carpick.carpickapp.util

import com.chibatching.kotpref.KotprefModel

object AppPref : KotprefModel() {
    var eventPopupCheck : Boolean by booleanPref(true)
    var today : Int by intPref(-1)
}
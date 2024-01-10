package com.carpick.carpickapp

import android.app.Application
import com.chibatching.kotpref.Kotpref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarPickApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Kotpref.init(this)
    }
}
package com.example.test

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(APIKEY)
    }
    companion object{
        const val APIKEY = "48624b30-9913-42af-99b4-e865b67ee8f2"
    }
}
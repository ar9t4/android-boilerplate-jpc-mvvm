package com.android.boilerplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Abdul Rahman on 02/05/2024
 */
@HiltAndroidApp
class Application: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
package com.maruchin.kmm.sharedmvi.android

import android.app.Application
import com.maruchin.kmm.sharedmvi.DemoSdk
import com.maruchin.kmm.sharedmvi.DemoConfig

class App : Application() {

    lateinit var sdk: DemoSdk

    override fun onCreate() {
        super.onCreate()
        val config = DemoConfig(settingsName = "demo_settings", context = applicationContext)
        sdk = DemoSdk(config)
    }
}

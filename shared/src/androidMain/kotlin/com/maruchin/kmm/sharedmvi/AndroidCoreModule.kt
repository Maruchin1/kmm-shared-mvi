package com.maruchin.kmm.sharedmvi

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

internal actual fun createSettings(config: DemoConfig): Settings {
    return SharedPreferencesSettings(
        delegate = config.context.getSharedPreferences(config.settingsName, Context.MODE_PRIVATE),
        commit = true
    )
}

internal actual fun createHttpEngineFactory(): HttpClientEngineFactory<*> = OkHttp

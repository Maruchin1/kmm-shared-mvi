package com.maruchin.kmm.sharedmvi

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

@OptIn(ExperimentalSettingsImplementation::class)
internal actual fun createSettings(config: DemoConfig): Settings {
    return KeychainSettings(config.settingsName)
}

internal actual fun createHttpEngineFactory(): HttpClientEngineFactory<*> = Darwin

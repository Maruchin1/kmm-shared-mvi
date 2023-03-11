package com.maruchin.kmm.sharedmvi

import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val coreModule = module {
    single { createSettings(get()) }
    single {
        HttpClient(createHttpEngineFactory()) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
    single { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
}

internal expect fun createSettings(config: DemoConfig): Settings

internal expect fun createHttpEngineFactory(): HttpClientEngineFactory<*>

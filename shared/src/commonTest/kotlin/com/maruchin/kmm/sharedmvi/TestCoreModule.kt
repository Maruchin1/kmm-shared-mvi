package com.maruchin.kmm.sharedmvi

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
internal val testCoreModule = module {
    single { MapSettings() } bind Settings::class
    single { UnconfinedTestDispatcher() } binds arrayOf(
        CoroutineDispatcher::class,
        TestDispatcher::class,
    )
    single { CoroutineScope(SupervisorJob() + get<TestDispatcher>()) }
}

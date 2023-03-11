package com.maruchin.kmm.sharedmvi

import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.domain.domainModule
import com.maruchin.kmm.sharedmvi.presentation.home.HomeViewModel
import com.maruchin.kmm.sharedmvi.presentation.login.LoginViewModel
import com.maruchin.kmm.sharedmvi.presentation.presentationModule
import org.koin.core.KoinApplication
import org.koin.dsl.module

class DemoSdk(config: DemoConfig) {

    private val koinApplication: KoinApplication =
        KoinApplication
            .init()
            .modules(
                presentationModule,
                domainModule,
                dataModule,
                coreModule,
                module { factory { config } }
            )

    val loginViewModel: LoginViewModel
        get() = getFromKoin()

    val homeViewModel: HomeViewModel
        get() = getFromKoin()

    private inline fun <reified T> getFromKoin(): T {
        return koinApplication.koin.get()
    }
}

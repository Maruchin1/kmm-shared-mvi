package com.maruchin.kmm.sharedmvi.presentation

import com.maruchin.kmm.sharedmvi.presentation.home.HomeViewModel
import com.maruchin.kmm.sharedmvi.presentation.login.LoginViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val presentationModule = module {
    factoryOf(::LoginViewModel)
    factoryOf(::HomeViewModel)
}

package com.maruchin.kmm.sharedmvi.presentation.login

import app.cash.turbine.test
import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.data.testApiModule
import com.maruchin.kmm.sharedmvi.domain.domainModule
import com.maruchin.kmm.sharedmvi.presentation.presentationModule
import com.maruchin.kmm.sharedmvi.testCoreModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : KoinTest {
    private val loginViewModel: LoginViewModel by inject()

    @BeforeTest
    fun before() {
        startKoin {
            modules(
                presentationModule,
                domainModule,
                dataModule,
                testCoreModule,
                testApiModule
            )
        }
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun after() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `Login success`() = runTest {
        // when
        loginViewModel.uiState.test {
            loginViewModel.login()

            // then
            awaitItem().run {
                assertFalse(isLoading)
                assertFalse(isLoggedIn)
                assertNull(errorMessage)
            }
            awaitItem().run {
                assertTrue(isLoading)
            }
            awaitItem().run {
                assertFalse(isLoading)
                assertTrue(isLoggedIn)
                assertNull(errorMessage)
            }
        }
    }
}

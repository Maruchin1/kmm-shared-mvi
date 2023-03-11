package com.maruchin.kmm.sharedmvi.presentation.home

import app.cash.turbine.test
import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.data.posts.FakePostsApi
import com.maruchin.kmm.sharedmvi.data.testApiModule
import com.maruchin.kmm.sharedmvi.domain.domainModule
import com.maruchin.kmm.sharedmvi.presentation.presentationModule
import com.maruchin.kmm.sharedmvi.sampleApiException
import com.maruchin.kmm.sharedmvi.samplePostsUiState
import com.maruchin.kmm.sharedmvi.testCoreModule
import io.ktor.client.plugins.*
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
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : KoinTest {
    private val fakePostsApi: FakePostsApi by inject()
    private val homeViewModel: HomeViewModel by inject()

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
    fun `Load data success`() = runTest {
        // when
        homeViewModel.uiState.test {

            // then
            awaitItem().run {
                assertContentEquals(emptyList(), posts)
                assertTrue(isLoading)
                assertFalse(isLoggedOut)
                assertNull(error)
            }
            awaitItem().run {
                assertContentEquals(samplePostsUiState, posts)
                assertFalse(isLoading)
                assertFalse(isLoggedOut)
                assertNull(error)
            }
        }
    }

    @Test
    fun `Logout success`() = runTest {
        // when
        homeViewModel.uiState.test {
            homeViewModel.logout()

            // then
            skipItems(2)
            awaitItem().run {
                assertTrue(isLoggedOut)
            }
        }
    }
}

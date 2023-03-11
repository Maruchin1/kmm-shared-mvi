package com.maruchin.kmm.sharedmvi.domain.session

import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.data.testApiModule
import com.maruchin.kmm.sharedmvi.domain.domainModule
import com.maruchin.kmm.sharedmvi.testCoreModule
import com.russhwolf.settings.Settings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class LogoutUserUseCaseTest : KoinTest {
    private val settings: Settings by inject()
    private val logoutUserUseCase: LogoutUserUseCase by inject()

    @BeforeTest
    fun before() {
        startKoin { modules(domainModule, dataModule, testCoreModule, testApiModule) }
    }

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Successful logout`() = runTest {
        // given
        settings.putLong("logged_user_id", 2)

        // when
        logoutUserUseCase()

        // then
        assertNull(settings.getLongOrNull("logged_user_id"))
    }
}

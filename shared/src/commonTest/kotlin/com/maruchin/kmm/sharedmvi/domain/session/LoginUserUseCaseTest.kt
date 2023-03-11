package com.maruchin.kmm.sharedmvi.domain.session

import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.data.testApiModule
import com.maruchin.kmm.sharedmvi.domain.domainModule
import com.maruchin.kmm.sharedmvi.sampleUsers
import com.maruchin.kmm.sharedmvi.sampleUsersJson
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
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUserUseCaseTest : KoinTest {
    private val settings: Settings by inject()
    private val loginUserUseCase: LoginUserUseCase by inject()

    @BeforeTest
    fun before() {
        startKoin { modules(domainModule, dataModule, testCoreModule, testApiModule) }
    }

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `User exists`() = runTest {
        // when
        val user = sampleUsers[1]
        loginUserUseCase(user.email)

        // then
        assertEquals(user.id, settings.getLongOrNull("logged_user_id"))
    }

    @Test
    fun `User doesn't exist`() = runTest {
        // when
        loginUserUseCase("abc")

        // then
        assertNull(settings.getLongOrNull("logged_user_id"))
    }
}

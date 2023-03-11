package com.maruchin.kmm.sharedmvi.data.users

import app.cash.turbine.test
import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.data.testApiModule
import com.maruchin.kmm.sharedmvi.sampleUsers
import com.maruchin.kmm.sharedmvi.testCoreModule
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
class UsersRepositoryTest : KoinTest {
    private val usersRepository: UsersRepository by inject()

    @BeforeTest
    fun before() {
        startKoin { modules(dataModule, testCoreModule, testApiModule) }
    }

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Get user`() = runTest {
        // when
        usersRepository.getUser(sampleUsers[0].id).test {

            // then
            assertEquals(sampleUsers[0], awaitItem())
        }
    }

    @Test
    fun `Get not existing user`() = runTest {
        // when
        usersRepository.getUser(123).test {

            // then
            assertNull(awaitItem())
        }
    }

    @Test
    fun `Find user`() = runTest {
        // when
        usersRepository.findUser(sampleUsers[1].email).test {

            // then
            assertEquals(sampleUsers[1], awaitItem())
        }
    }

    @Test
    fun `Find not existing user`() = runTest {
        // when
        usersRepository.findUser("abc").test {

            // then
            assertNull(awaitItem())
        }
    }
}

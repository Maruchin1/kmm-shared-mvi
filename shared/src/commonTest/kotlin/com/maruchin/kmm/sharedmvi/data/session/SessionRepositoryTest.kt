package com.maruchin.kmm.sharedmvi.data.session

import app.cash.turbine.test
import com.maruchin.kmm.sharedmvi.data.dataModule
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
class SessionRepositoryTest : KoinTest {
    private val sessionRepository: SessionRepository by inject()

    @BeforeTest
    fun before() {
        startKoin { modules(dataModule, testCoreModule) }
    }

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Session not saved`() = runTest {
        // when
        sessionRepository.session.test {

            // then
            assertNull(awaitItem())
        }
    }

    @Test
    fun `Save session`() = runTest {
        // when
        val savedSession = Session.forUser(sampleUsers[0])
        sessionRepository.session.test {
            sessionRepository.saveSession(savedSession)

            // then
            assertNull(awaitItem())
            assertEquals(savedSession, awaitItem())
        }
    }

    @Test
    fun `Change session`() = runTest {
        // when
        val firstSession = Session.forUser(sampleUsers[0])
        val secondSession = Session.forUser(sampleUsers[1])
        sessionRepository.session.test {
            sessionRepository.saveSession(firstSession)
            sessionRepository.saveSession(secondSession)

            // then
            assertNull(awaitItem())
            assertEquals(firstSession, awaitItem())
            assertEquals(secondSession, awaitItem())
        }
    }
}

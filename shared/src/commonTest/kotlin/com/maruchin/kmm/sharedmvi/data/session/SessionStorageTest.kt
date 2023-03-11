package com.maruchin.kmm.sharedmvi.data.session

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
class SessionStorageTest : KoinTest {
    private val sessionStorage: SessionStorage by inject()

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
        val session = sessionStorage.getSession()

        // then
        assertNull(session)
    }

    @Test
    fun `Session saved`() = runTest {
        // when
        val savedSession = Session.forUser(sampleUsers[0])
        sessionStorage.saveSession(savedSession)
        val session = sessionStorage.getSession()

        // then
        assertEquals(savedSession, session)
    }

    @Test
    fun `Session changed`() = runTest {
        // when
        val firstSession = Session.forUser(sampleUsers[0])
        val secondSession = Session.forUser(sampleUsers[1])
        sessionStorage.saveSession(firstSession)
        sessionStorage.saveSession(secondSession)
        val session = sessionStorage.getSession()

        // then
        assertEquals(secondSession, session)
    }
}

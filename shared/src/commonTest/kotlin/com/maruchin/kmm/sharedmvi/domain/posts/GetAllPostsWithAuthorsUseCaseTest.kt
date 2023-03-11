package com.maruchin.kmm.sharedmvi.domain.posts

import app.cash.turbine.test
import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.data.testApiModule
import com.maruchin.kmm.sharedmvi.domain.domainModule
import com.maruchin.kmm.sharedmvi.samplePostsWithAuthors
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
import kotlin.test.assertContentEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllPostsWithAuthorsUseCaseTest : KoinTest {
    private val getAllPostsWithAuthorsUseCase: GetAllPostsWithAuthorsUseCase by inject()

    @BeforeTest
    fun before() {
        startKoin { modules(domainModule, dataModule, testCoreModule, testApiModule) }
    }

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Combine posts with authors`() = runTest {
        // when
        getAllPostsWithAuthorsUseCase().test {

            // then
            assertContentEquals(samplePostsWithAuthors, awaitItem())
        }
    }
}

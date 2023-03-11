package com.maruchin.kmm.sharedmvi.data.posts

import app.cash.turbine.test
import com.maruchin.kmm.sharedmvi.data.dataModule
import com.maruchin.kmm.sharedmvi.data.testApiModule
import com.maruchin.kmm.sharedmvi.sampleFreshPosts
import com.maruchin.kmm.sharedmvi.sampleFreshPostsJson
import com.maruchin.kmm.sharedmvi.samplePosts
import com.maruchin.kmm.sharedmvi.testCoreModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
internal class PostsRepositoryTest : KoinTest {
    private val fakePostsApi: FakePostsApi by inject()
    private val postsRepository: PostsRepository by inject()

    @BeforeTest
    fun before() {
        startKoin { modules(dataModule, testApiModule, testCoreModule) }
    }

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Get all posts`() = runTest {
        // when
        postsRepository.getAllPosts().test {

            // then
            assertContentEquals(samplePosts, awaitItem())
        }
    }

    @Test
    fun `Get all posts second time without refreshing`() = runTest {
        // when
        postsRepository.getAllPosts().first()
        fakePostsApi.allPosts = sampleFreshPostsJson
        postsRepository.getAllPosts().test {

            // then
            assertContentEquals(samplePosts, awaitItem())
        }
    }

    @Test
    fun `Get all posts with refreshing`() = runTest {
        // when
        postsRepository.getAllPosts().first()
        fakePostsApi.allPosts = sampleFreshPostsJson
        postsRepository.getAllPosts(fresh = true).test {

            // then
            assertContentEquals(sampleFreshPosts, awaitItem())
        }
    }
}

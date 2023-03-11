package com.maruchin.kmm.sharedmvi.data.posts

import com.maruchin.kmm.sharedmvi.samplePosts
import com.maruchin.kmm.sharedmvi.samplePostsJson
import kotlin.test.Test
import kotlin.test.assertEquals

internal class PostJsonKtTest {

    @Test
    fun `Json as model`() {
        // when
        val model = samplePostsJson[0].asModel()

        // then
        assertEquals(samplePosts[0], model)
    }
}

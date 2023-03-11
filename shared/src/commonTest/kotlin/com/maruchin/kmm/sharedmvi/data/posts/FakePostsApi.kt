package com.maruchin.kmm.sharedmvi.data.posts

import com.maruchin.kmm.sharedmvi.samplePostsJson

internal class FakePostsApi : PostsApi {

    var allPosts: List<PostJson> = samplePostsJson
    var allPostsException: Throwable? = null

    override suspend fun fetchAllPosts(): List<PostJson> {
        return allPostsException?.let { throw it } ?: allPosts
    }
}

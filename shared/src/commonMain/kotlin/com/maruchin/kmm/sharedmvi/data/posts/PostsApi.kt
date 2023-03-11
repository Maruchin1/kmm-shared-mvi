package com.maruchin.kmm.sharedmvi.data.posts

internal interface PostsApi {

    suspend fun fetchAllPosts(): List<PostJson>
}

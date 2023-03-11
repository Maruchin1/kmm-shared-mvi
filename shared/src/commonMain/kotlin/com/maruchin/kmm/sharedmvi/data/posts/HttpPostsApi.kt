package com.maruchin.kmm.sharedmvi.data.posts

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

internal class HttpPostsApi(private val client: HttpClient) : PostsApi {

    override suspend fun fetchAllPosts(): List<PostJson> {
        return client.get("https://jsonplaceholder.typicode.com/posts").body()
    }
}

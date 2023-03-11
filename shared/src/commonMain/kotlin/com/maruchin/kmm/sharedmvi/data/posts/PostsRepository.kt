package com.maruchin.kmm.sharedmvi.data.posts

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

internal class PostsRepository(scope: CoroutineScope, private val postsApi: PostsApi) {

    private val refresh = MutableSharedFlow<Unit>()

    private val allPosts: StateFlow<List<Post>> =
        refresh
            .onStart { emit(Unit) }
            .map { postsApi.fetchAllPosts() }
            .map { it.map(PostJson::asModel) }
            .stateIn(scope, SharingStarted.Lazily, emptyList())

    fun getAllPosts(fresh: Boolean = false): Flow<List<Post>> {
        return allPosts.onStart { if (fresh) refresh.emit(Unit) }
    }
}

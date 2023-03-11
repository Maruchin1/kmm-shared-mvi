package com.maruchin.kmm.sharedmvi.presentation.home

import com.maruchin.kmm.sharedmvi.domain.posts.PostWithAuthor

data class HomeUiState(
    val posts: List<PostUiState>,
    val isLoading: Boolean,
    val isLoggedOut: Boolean,
    val error: Throwable?,
) {

    companion object {

        fun default() = HomeUiState(
            posts = emptyList(),
            isLoading = true,
            isLoggedOut = false,
            error = null,
        )
    }
}

data class PostUiState(
    val id: Long,
    val title: String,
    val authorName: String,
    val body: String,
)

internal fun PostWithAuthor.asUiState() = PostUiState(
    id = post.id,
    title = post.title,
    authorName = author.name,
    body = post.body,
)

package com.maruchin.kmm.sharedmvi.domain.posts

import com.maruchin.kmm.sharedmvi.data.posts.PostsRepository
import com.maruchin.kmm.sharedmvi.data.users.UsersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetAllPostsWithAuthorsUseCase(
    private val postsRepository: PostsRepository,
    private val usersRepository: UsersRepository,
) {

    operator fun invoke(): Flow<List<PostWithAuthor>> {
        return postsRepository.getAllPosts().flatMapLatest { posts ->
            posts.map { post ->
                usersRepository.getUser(post.userId).map { user ->
                    user?.let { PostWithAuthor(post, it) }
                }
            }.let { flows ->
                combine(flows) { it.filterNotNull() }
            }.distinctUntilChanged()
        }
    }
}

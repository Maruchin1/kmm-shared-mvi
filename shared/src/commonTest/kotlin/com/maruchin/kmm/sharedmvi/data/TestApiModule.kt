package com.maruchin.kmm.sharedmvi.data

import com.maruchin.kmm.sharedmvi.data.posts.FakePostsApi
import com.maruchin.kmm.sharedmvi.data.posts.PostsApi
import com.maruchin.kmm.sharedmvi.data.users.FakeUsersApi
import com.maruchin.kmm.sharedmvi.data.users.UsersApi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.binds
import org.koin.dsl.module

internal val testApiModule = module {
    singleOf(::FakePostsApi) binds arrayOf(PostsApi::class, FakePostsApi::class)
    singleOf(::FakeUsersApi) binds arrayOf(UsersApi::class, FakeUsersApi::class)
}

package com.maruchin.kmm.sharedmvi.data

import com.maruchin.kmm.sharedmvi.data.posts.HttpPostsApi
import com.maruchin.kmm.sharedmvi.data.posts.PostsApi
import com.maruchin.kmm.sharedmvi.data.posts.PostsRepository
import com.maruchin.kmm.sharedmvi.data.session.SessionRepository
import com.maruchin.kmm.sharedmvi.data.session.SessionStorage
import com.maruchin.kmm.sharedmvi.data.users.HttpUsersApi
import com.maruchin.kmm.sharedmvi.data.users.UsersApi
import com.maruchin.kmm.sharedmvi.data.users.UsersRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModule = module {
    singleOf(::SessionRepository)
    factoryOf(::SessionStorage)

    singleOf(::UsersRepository)
    factoryOf(::HttpUsersApi) bind UsersApi::class

    singleOf(::PostsRepository)
    factoryOf(::HttpPostsApi) bind PostsApi::class
}

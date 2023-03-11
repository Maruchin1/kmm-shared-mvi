package com.maruchin.kmm.sharedmvi.domain

import com.maruchin.kmm.sharedmvi.domain.posts.GetAllPostsWithAuthorsUseCase
import com.maruchin.kmm.sharedmvi.domain.session.LoginUserUseCase
import com.maruchin.kmm.sharedmvi.domain.session.LogoutUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val domainModule = module {
    factoryOf(::LoginUserUseCase)
    factoryOf(::LogoutUserUseCase)

    factoryOf(::GetAllPostsWithAuthorsUseCase)
}

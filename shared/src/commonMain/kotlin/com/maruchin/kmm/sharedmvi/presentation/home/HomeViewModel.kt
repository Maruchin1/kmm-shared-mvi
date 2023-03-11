package com.maruchin.kmm.sharedmvi.presentation.home

import com.maruchin.kmm.sharedmvi.domain.posts.GetAllPostsWithAuthorsUseCase
import com.maruchin.kmm.sharedmvi.domain.session.LogoutUserUseCase
import com.maruchin.kmm.sharedmvi.mvi.KmmStateFlow
import com.maruchin.kmm.sharedmvi.mvi.KmmViewModel
import com.maruchin.kmm.sharedmvi.mvi.asKmmStateFlow
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.native.ObjCName

@ObjCName("HomeViewModelDelegate")
class HomeViewModel internal constructor(
    private val getAllPostsWithAuthorsUseCase: GetAllPostsWithAuthorsUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
) : KmmViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, e -> handleException(e) }
    private val _uiState = MutableStateFlow(HomeUiState.default())
    val uiState: KmmStateFlow<HomeUiState> = _uiState.asKmmStateFlow()

    init {
        collectPosts()
    }

    fun logout() = scope.launch(exceptionHandler) {
        _uiState.update { it.copy(isLoading = true) }
        logoutUserUseCase()
        _uiState.update { it.copy(isLoading = false, isLoggedOut = true) }
    }

    private fun collectPosts() = scope.launch(exceptionHandler) {
        getAllPostsWithAuthorsUseCase().collectLatest { posts ->
            val uiPosts = posts.map { it.asUiState() }
            _uiState.update { it.copy(isLoading = false, posts = uiPosts) }
        }
    }

    private fun handleException(e: Throwable) {
        _uiState.update { it.copy(isLoading = false, error = e) }
    }
}

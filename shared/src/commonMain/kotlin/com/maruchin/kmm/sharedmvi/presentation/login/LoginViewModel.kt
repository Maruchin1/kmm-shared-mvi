package com.maruchin.kmm.sharedmvi.presentation.login

import com.maruchin.kmm.sharedmvi.domain.session.LoginUserUseCase
import com.maruchin.kmm.sharedmvi.mvi.KmmStateFlow
import com.maruchin.kmm.sharedmvi.mvi.KmmViewModel
import com.maruchin.kmm.sharedmvi.mvi.asKmmStateFlow
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.native.ObjCName

@ObjCName("LoginViewModelDelegate")
class LoginViewModel internal constructor(
    private val loginUserUseCase: LoginUserUseCase
) : KmmViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, e -> handleException(e) }
    private val _uiState = MutableStateFlow(LoginUiState.default())
    val uiState: KmmStateFlow<LoginUiState> = _uiState.asKmmStateFlow()

    fun login() = scope.launch(exceptionHandler) {
        _uiState.update { it.copy(isLoading = true) }
        val email = _uiState.value.email
        loginUserUseCase(email)
        _uiState.update { it.copy(isLoading = false, isLoggedIn = true) }
    }

    private fun handleException(e: Throwable) {
        _uiState.update { it.copy(isLoading = false, errorMessage = e) }
    }
}

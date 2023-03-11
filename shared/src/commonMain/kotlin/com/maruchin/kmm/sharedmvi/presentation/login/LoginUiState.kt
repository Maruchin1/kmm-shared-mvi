package com.maruchin.kmm.sharedmvi.presentation.login

private const val DEMO_USER_EMAIL = "Sincere@april.biz"

data class LoginUiState(
    val email: String,
    val isLoading: Boolean,
    val isLoggedIn: Boolean,
    val errorMessage: Throwable?,
) {

    companion object {

        fun default() = LoginUiState(
            email = DEMO_USER_EMAIL,
            isLoading = false,
            isLoggedIn = false,
            errorMessage = null,
        )
    }
}

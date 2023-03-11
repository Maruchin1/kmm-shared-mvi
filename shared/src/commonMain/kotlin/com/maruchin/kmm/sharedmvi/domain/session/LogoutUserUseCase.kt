package com.maruchin.kmm.sharedmvi.domain.session

import com.maruchin.kmm.sharedmvi.data.session.SessionRepository

internal class LogoutUserUseCase(
    private val sessionRepository: SessionRepository
) {

    suspend operator fun invoke() {
        sessionRepository.saveSession(null)
    }
}

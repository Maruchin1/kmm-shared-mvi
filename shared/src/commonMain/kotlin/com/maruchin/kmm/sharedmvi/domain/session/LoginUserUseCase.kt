package com.maruchin.kmm.sharedmvi.domain.session

import com.maruchin.kmm.sharedmvi.data.session.Session
import com.maruchin.kmm.sharedmvi.data.session.SessionRepository
import com.maruchin.kmm.sharedmvi.data.users.UsersRepository
import kotlinx.coroutines.flow.first

internal class LoginUserUseCase(
    private val usersRepository: UsersRepository,
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(email: String) {
        val user = usersRepository.findUser(email).first()
        if (user != null) {
            val session = Session.forUser(user)
            sessionRepository.saveSession(session)
        }
    }
}

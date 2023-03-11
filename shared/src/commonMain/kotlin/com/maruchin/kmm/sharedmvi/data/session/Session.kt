package com.maruchin.kmm.sharedmvi.data.session

import com.maruchin.kmm.sharedmvi.data.users.User

internal data class Session(val loggedUserId: Long) {

    companion object {

        fun forUser(user: User) = Session(loggedUserId = user.id)
    }
}

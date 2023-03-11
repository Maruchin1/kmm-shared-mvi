package com.maruchin.kmm.sharedmvi.data.session

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

private const val LOGGED_USER_ID = "logged_user_id"

internal class SessionStorage(private val settings: Settings) : Mutex by Mutex() {

    suspend fun getSession(): Session? = withLock {
        settings
            .getLongOrNull(LOGGED_USER_ID)
            ?.let { Session(it) }
    }

    suspend fun saveSession(session: Session?) = withLock {
        settings[LOGGED_USER_ID] = session?.loggedUserId
    }
}

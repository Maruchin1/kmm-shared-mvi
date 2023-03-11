package com.maruchin.kmm.sharedmvi.data.session

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class SessionRepository(
    private val sessionStorage: SessionStorage,
    private val scope: CoroutineScope,
) {

    private val refresh = MutableSharedFlow<Unit>()

    val session: Flow<Session?> =
        refresh
            .onStart { emit(Unit) }
            .map { sessionStorage.getSession() }
            .stateIn(scope, SharingStarted.Lazily, null)

    suspend fun saveSession(session: Session?) = scope.launch {
        sessionStorage.saveSession(session)
        refresh.emit(Unit)
    }
}

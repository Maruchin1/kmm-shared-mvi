package com.maruchin.kmm.sharedmvi.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

actual class KmmStateFlow<T> actual constructor(
    private val source: StateFlow<T>
) : StateFlow<T> by source {

    fun subscribe(onEach: (T) -> Unit, onCompletion: (Throwable?) -> Unit): KmmSubscription {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        source
            .onEach { onEach(it) }
            .catch { onCompletion(it) }
            .onCompletion { onCompletion(null) }
            .launchIn(scope)
        return KmmSubscription { scope.cancel() }
    }
}

package com.maruchin.kmm.sharedmvi.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class KmmViewModel {
    protected actual val scope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onCleared() {
        scope.cancel()
    }
}

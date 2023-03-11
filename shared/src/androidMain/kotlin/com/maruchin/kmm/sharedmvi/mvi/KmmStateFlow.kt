package com.maruchin.kmm.sharedmvi.mvi

import kotlinx.coroutines.flow.StateFlow

actual class KmmStateFlow<T> actual constructor(source: StateFlow<T>) : StateFlow<T> by source

package com.maruchin.kmm.sharedmvi.mvi

import kotlinx.coroutines.flow.StateFlow

expect class KmmStateFlow<T>(source: StateFlow<T>) : StateFlow<T>

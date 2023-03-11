package com.maruchin.kmm.sharedmvi.mvi

import kotlinx.coroutines.CoroutineScope

expect abstract class KmmViewModel constructor() {
    protected val scope: CoroutineScope
}

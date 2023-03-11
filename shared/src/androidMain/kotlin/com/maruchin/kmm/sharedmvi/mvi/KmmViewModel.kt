package com.maruchin.kmm.sharedmvi.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class KmmViewModel : ViewModel() {
    protected actual val scope: CoroutineScope
        get() = viewModelScope
}

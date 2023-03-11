package com.maruchin.kmm.sharedmvi.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.maruchin.kmm.sharedmvi.android.ui.MainCoordinator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainCoordinator()
            }
        }
    }
}

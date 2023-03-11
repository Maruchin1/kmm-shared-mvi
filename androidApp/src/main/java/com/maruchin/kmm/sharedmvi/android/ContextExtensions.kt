package com.maruchin.kmm.sharedmvi.android

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun Context.getDemoSdk() = (applicationContext as App).sdk

@Composable
fun getDemoSdk() = LocalContext.current.getDemoSdk()

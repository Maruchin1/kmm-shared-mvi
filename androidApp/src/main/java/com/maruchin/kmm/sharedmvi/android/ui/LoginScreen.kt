@file:OptIn(ExperimentalMaterial3Api::class)

package com.maruchin.kmm.sharedmvi.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maruchin.kmm.sharedmvi.android.getDemoSdk
import com.maruchin.kmm.sharedmvi.presentation.login.LoginUiState

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val sdk = getDemoSdk()
    val viewModel = viewModel(initializer = { sdk.loginViewModel })
    val state by viewModel.uiState.collectAsState()
    LoginScreen(state = state, onLogin = viewModel::login, onLoginSuccess = onLoginSuccess)
}

@Composable
private fun LoginScreen(state: LoginUiState, onLogin: () -> Unit, onLoginSuccess: () -> Unit) {
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) onLoginSuccess()
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Login") })
        }
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Welcome",
                style = typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
            )
            Text(
                text = state.email,
                style = typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            )
            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
    LoadingView(visible = state.isLoading)
}

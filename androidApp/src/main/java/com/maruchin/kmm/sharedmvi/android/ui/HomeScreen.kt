@file:OptIn(ExperimentalMaterial3Api::class)

package com.maruchin.kmm.sharedmvi.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maruchin.kmm.sharedmvi.android.getDemoSdk
import com.maruchin.kmm.sharedmvi.presentation.home.HomeUiState

@Composable
fun HomeScreen(onLogoutSuccess: () -> Unit) {
    val sdk = getDemoSdk()
    val viewModel = viewModel { sdk.homeViewModel }
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state = state, onLogout = viewModel::logout, onLogoutSuccess = onLogoutSuccess)
}

@Composable
private fun HomeScreen(state: HomeUiState, onLogout: () -> Unit, onLogoutSuccess: () -> Unit) {
    LaunchedEffect(state.isLoggedOut) {
        if (state.isLoggedOut) onLogoutSuccess()
    }
    Scaffold(
        topBar = {
            TopAppBar(onLogout = onLogout)
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(state.posts, key = { it.id }) { post ->
                PostView(
                    title = post.title,
                    authorName = post.authorName,
                    body = post.body,
                )
            }
        }
    }
    LoadingView(state.isLoading)
}

@Composable
private fun TopAppBar(onLogout: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Posts")
        },
        actions = {
            TextButton(onClick = onLogout) {
                Text(text = "Logout")
            }
        }
    )
}

@Composable
private fun PostView(title: String, authorName: String, body: String) {
    Column {
        Text(
            text = title,
            style = typography.titleMedium,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 4.dp)
        )
        Text(
            text = authorName,
            style = typography.labelMedium,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
        )
        Text(
            text = body,
            style = typography.bodyMedium,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 16.dp)
        )
        Divider()
    }
}

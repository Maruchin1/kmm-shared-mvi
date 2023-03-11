package com.maruchin.kmm.sharedmvi.data.users

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class UsersRepository(private val usersApi: UsersApi, private val scope: CoroutineScope) {

    private val users = MutableStateFlow<Map<Long, User>>(emptyMap())

    fun getUser(id: Long): Flow<User?> {
        return users.map { it[id] }.onStart {
            if (!hasUser(id)) loadUser(id).join()
        }
    }

    fun findUser(email: String): Flow<User?> {
        return users.map { users ->
            users.values.find { it.email == email }
        }.onStart {
            if (!hasUser(email)) loadUser(email).join()
        }
    }

    private fun hasUser(id: Long): Boolean {
        return users.value.containsKey(id)
    }

    private fun hasUser(email: String): Boolean {
        return users.value.values.find { it.email == email } != null
    }

    private fun loadUser(id: Long) = scope.launch {
        usersApi.fetchUser(id)?.asModel()?.let { user ->
            users.update { it + Pair(user.id, user) }
        }
    }

    private fun loadUser(email: String) = scope.launch {
        usersApi.fetchUsersByEmail(email).firstOrNull()?.asModel()?.let { user ->
            users.update { it + Pair(user.id, user) }
        }
    }
}

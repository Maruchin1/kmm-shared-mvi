package com.maruchin.kmm.sharedmvi.data.users

internal interface UsersApi {

    suspend fun fetchUser(id: Long): UserJson?

    suspend fun fetchUsersByEmail(email: String): List<UserJson>
}

package com.maruchin.kmm.sharedmvi.data.users

import com.maruchin.kmm.sharedmvi.sampleUsersJson

internal class FakeUsersApi : UsersApi {

    var users: List<UserJson> = sampleUsersJson

    override suspend fun fetchUser(id: Long): UserJson? {
        return users.find { it.id == id }
    }

    override suspend fun fetchUsersByEmail(email: String): List<UserJson> {
        return users.filter { it.email == email }
    }
}

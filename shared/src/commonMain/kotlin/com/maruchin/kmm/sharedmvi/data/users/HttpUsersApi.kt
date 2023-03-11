package com.maruchin.kmm.sharedmvi.data.users

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

internal class HttpUsersApi(private val client: HttpClient) : UsersApi {

    override suspend fun fetchUser(id: Long): UserJson? {
        return client.get("https://jsonplaceholder.typicode.com/users/$id").body()
    }

    override suspend fun fetchUsersByEmail(email: String): List<UserJson> {
        return client.get("https://jsonplaceholder.typicode.com/users") {
            url {
                parameters.append("email", email)
            }
        }.body()
    }
}

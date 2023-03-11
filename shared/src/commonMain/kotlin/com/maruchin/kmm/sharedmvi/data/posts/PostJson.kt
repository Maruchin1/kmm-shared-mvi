package com.maruchin.kmm.sharedmvi.data.posts

import kotlinx.serialization.Serializable

@Serializable
internal data class PostJson(val id: Long, val userId: Long, val title: String, val body: String)

internal fun PostJson.asModel() = Post(id = id, userId = userId, title = title, body = body)

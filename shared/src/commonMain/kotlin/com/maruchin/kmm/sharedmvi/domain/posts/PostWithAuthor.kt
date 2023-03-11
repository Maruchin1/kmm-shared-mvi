package com.maruchin.kmm.sharedmvi.domain.posts

import com.maruchin.kmm.sharedmvi.data.posts.Post
import com.maruchin.kmm.sharedmvi.data.users.User

internal data class PostWithAuthor(val post: Post, val author: User)

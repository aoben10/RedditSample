package com.theobencode.victoroben.sampleproject.network.models.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostsResponse(
    @Json(name = "data")
    val postData: Data
)
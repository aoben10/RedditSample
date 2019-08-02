package com.theobencode.victoroben.sampleproject.network.models.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SecureMedia(
    @Json(name = "reddit_video")
    val redditVideo: RedditVideo?
)
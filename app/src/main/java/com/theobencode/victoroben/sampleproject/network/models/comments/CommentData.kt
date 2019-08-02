package com.theobencode.victoroben.sampleproject.network.models.comments

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentData(

    @Json(name = "author")
    val author: String?,
    @Json(name = "body")
    val body: String?,
    @Json(name = "score")
    val score: Int?
)
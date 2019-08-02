package com.theobencode.victoroben.sampleproject.network.models.comments

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Children(
    @Json(name = "data")
    val `data`: CommentData,
    @Json(name = "kind")
    val kind: String?
)
package com.theobencode.victoroben.sampleproject.network.models.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Children(
    @Json(name = "data")
    val `data`: DataX,
    @Json(name = "kind")
    val kind: String?
)
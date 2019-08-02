package com.theobencode.victoroben.sampleproject.network.models.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Preview(
    @Json(name = "enabled")
    val enabled: Boolean?,
    @Json(name = "images")
    val images: List<Image?>?
)
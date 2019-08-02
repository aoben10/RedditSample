package com.theobencode.victoroben.sampleproject.network.models.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "postId")
    val id: String?,
    @Json(name = "resolutions")
    val resolutions: List<Resolution?>?,
    @Json(name = "source")
    val source: Source?,
    @Json(name = "variants")
    val variants: Variants?
)
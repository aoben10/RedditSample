package com.theobencode.victoroben.sampleproject.network.models.comments

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "after")
    val after: Any?,
    @Json(name = "before")
    val before: Any?,
    @Json(name = "children")
    val children: List<Children>,
    @Json(name = "dist")
    val dist: Any?,
    @Json(name = "modhash")
    val modhash: String?
)
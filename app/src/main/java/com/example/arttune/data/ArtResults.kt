package com.example.arttune.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtResults (
    @Json(name = "data") val artPieces: List<ArtPiece>,
)



@JsonClass(generateAdapter = true)
data class ArtPiece (
    val id: Int,
    val title: String,
    val timestamp: String,
    @Json(name = "api_link") val link: String
) : java.io.Serializable


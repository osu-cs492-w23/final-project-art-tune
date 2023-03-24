package com.example.arttune.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtResults (
    @Json(name = "data") val artPieces: List<ArtPiece>,
)

@JsonClass(generateAdapter = true)
data class ArtPiece (
    @Json(name = "api_link") val link: String,
    val id: Int,
    val title: String,
    val timestamp: String,
    @Json(name = "date_display") val date: String? = "",
    @Json(name = "artist_title") val artist: String? = "",
    @Json(name = "medium_display") val medium: String? = ""
) : java.io.Serializable


@JsonClass(generateAdapter = true)
data class ArtWork (
    @Json(name = "data") val piece: ArtWorkInfo
) : java.io.Serializable
@JsonClass(generateAdapter = true)
data class ArtWorkInfo (
    @Json(name = "image_id") val artid: String
) : java.io.Serializable

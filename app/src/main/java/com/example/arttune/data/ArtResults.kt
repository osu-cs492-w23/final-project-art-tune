package com.example.arttune.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ArtResults (
    @Json(name = "data") val artPieces: List<ArtPiece>,
)

@JsonClass(generateAdapter = true)
data class ArtPiece (
    val link: String,
    val id: Int,
    val title: String,
    val timestamp: String,
    val date: String,
    val artist: String,
    val medium: String
) : Serializable


@JsonClass(generateAdapter = true)
data class ArtWork (
    @Json(name = "data") val piece: ArtWorkInfo
)

@JsonClass(generateAdapter = true)
data class ArtWorkInfo (
    val date_display: String,
    val artist_title: String,
    val medium_display: String,
    val id: Int,
    val image_id: String,
    val api_link: String,
    val title: String,
    val timestamp: String
)

class ArtJsonAdapter {
    @FromJson
    fun ArtPieceFromJson(art: ArtWorkInfo) = ArtPiece(
        id = art.id,
        link = art.api_link,
        title = art.title,
        artist = art.artist_title,
        timestamp = art.timestamp,
        date = art.date_display,
        medium = art.medium_display
    )

    @ToJson
    fun ArtPieceToJson(art: ArtPiece): String {
        throw UnsupportedOperationException("encoding ArtPiece to JSON is not supported")
    }
}
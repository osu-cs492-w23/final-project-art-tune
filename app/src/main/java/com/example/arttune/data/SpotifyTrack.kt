package com.example.arttune.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import java.io.Serializable

data class SpotifyTrack(
    val album: String,
    val artists: String,
    val genre: String,
    val releaseDate: String,
    val length: Int,
    val id: String,
    val trackTitle: String
) : Serializable

@JsonClass(generateAdapter = true)
data class SpotifyTrackItemsJson(
    val album: AlbumJson,
    val artists: ArtistListJson,
    val duration_ms: Int,
    val id: String,
    val name: String
)

@JsonClass(generateAdapter = true)
data class AlbumJson(
    val name: String,
    val release_date: String,
    val genres: List<String>
)

@JsonClass(generateAdapter = true)
data class ArtistListJson(
    val items: List<ArtistJson>
)

@JsonClass(generateAdapter = true)
data class ArtistJson(
    val genres: List<String>,
    val name: String,
    val id: String
)

class SpotifyJsonAdapter {
    @FromJson
    fun SpotifyTrackFromJson(track: SpotifyTrackItemsJson) = SpotifyTrack(
        album = track.album.name,
        artists = track.artists.items[0].name,
        genre = track.album.genres[0],
        releaseDate = track.album.release_date,
        id = track.id,
        trackTitle = track.name,
        length = track.duration_ms / 1000
    )

    @ToJson
    fun SpotifyTrackToJson(track: SpotifyTrackItemsJson): String {
        throw UnsupportedOperationException("encoding SpotifyTrack to JSON is not supported")
    }
}
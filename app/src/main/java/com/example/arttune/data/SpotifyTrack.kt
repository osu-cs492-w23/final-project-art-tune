package com.example.arttune.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class SpotifyTrack(
    val album: String,
    val artists: List<ArtistJson>,
    val genre: String,
    val releaseDate: String,
    val length: Int,
    val id: String,
    val trackTitle: String
) : java.io.Serializable

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
        artists = track.artists.items,
        genre = track.album.genres[0],
        releaseDate = track.album.release_date,
        id = track.id,
        trackTitle = track.name,
        length = track.duration_ms / 1000
    )
}
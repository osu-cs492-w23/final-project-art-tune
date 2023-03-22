package com.example.arttune.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrackResults(
    val items: List<SpotifyTrack>,
    val href: String
)
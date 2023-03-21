package com.example.arttune.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtResults (
    val href: String
)


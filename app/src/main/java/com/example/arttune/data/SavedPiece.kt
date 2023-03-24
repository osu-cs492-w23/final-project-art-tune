package com.example.arttune.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedPiece(
    @PrimaryKey val songName: String,
    val songArtist: String,
    val artName: String,
    val artArtist: String,
    var imgUrl: String,
    var previewUrl: String
)

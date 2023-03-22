package com.example.arttune.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedPiece(
    @PrimaryKey val songArtist: List<String>,
    val imgUrl: String
)

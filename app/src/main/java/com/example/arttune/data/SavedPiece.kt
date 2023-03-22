package com.example.arttune.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedPiece(
    @PrimaryKey val songName: String,
    val artist: String,
    val imgUrl: String
)

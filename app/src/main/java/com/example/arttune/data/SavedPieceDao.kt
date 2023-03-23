package com.example.arttune.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedPieceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedPiece: SavedPiece)

    @Delete
    suspend fun delete(savedPiece: SavedPiece)

    @Query("SELECT * FROM SavedPiece")
    fun getAllPieces(): Flow<List<SavedPiece>>
}
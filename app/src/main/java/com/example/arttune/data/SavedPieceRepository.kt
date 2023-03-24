package com.example.arttune.data

class SavedPieceRepository(
    private val dao: SavedPieceDao
) {
    suspend fun insertSavedPiece(savedPiece: SavedPiece) = dao.insert(savedPiece)

    suspend fun deleteSavedPiece(savedPiece: SavedPiece) = dao.delete(savedPiece)

    fun getAllSavedPieces() = dao.getAllPieces()

    fun getPieceByName(name: String) = dao.getPieceByName(name)
}
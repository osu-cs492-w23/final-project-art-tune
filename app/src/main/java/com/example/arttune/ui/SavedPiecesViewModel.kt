package com.example.arttune.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.arttune.data.AppDatabase
import com.example.arttune.data.SavedPiece
import com.example.arttune.data.SavedPieceRepository
import kotlinx.coroutines.launch

class SavedPiecesViewModel(application: Application): AndroidViewModel(application) {
    private val repository = SavedPieceRepository(
        AppDatabase.getInstance(application).savedPieceDao()
    )

    val savedPieces = repository.getAllSavedPieces().asLiveData()

    fun addSavedPiece(savedPiece: SavedPiece){
        viewModelScope.launch {
            repository.insertSavedPiece(savedPiece)
        }
    }

    fun removeSavedPiece(savedPiece: SavedPiece) {
        viewModelScope.launch {
            repository.deleteSavedPiece(savedPiece)
        }
    }

    fun getPieceByName(name: String) =repository.getPieceByName(name).asLiveData()
}
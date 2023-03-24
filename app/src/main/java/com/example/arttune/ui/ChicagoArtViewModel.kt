package com.example.arttune.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arttune.api.ChicagoArtService
import com.example.arttune.api.SpotifyService
import com.example.arttune.data.*
import kotlinx.coroutines.launch

class ChicagoArtViewModel : ViewModel() {
    private val repository = ArtPieceRepository(ChicagoArtService.create())
    private val _searchResults = MutableLiveData<List<ArtPiece>?>(null)
    val searchResults: LiveData<List<ArtPiece>?> = _searchResults

    private val _loadingStatus = MutableLiveData<LoadingStatus>(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus
    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _artInfo = MutableLiveData<ArtWorkInfo>(null)
    val artInfo: LiveData<ArtWorkInfo> = _artInfo

    fun loadSearch(q: String) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            _errorMessage.value = null
            val result = repository.loadArtSearch(q)
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
            _searchResults.value = result.getOrNull()
            _errorMessage.value = result.exceptionOrNull()?.message
            Log.d("loadSearch", searchResults.value?.get(0)?.medium.toString())
        }
    }

    fun loadInfo(q: String){
        viewModelScope.launch {
            val result = repository.loadArtInfo(q)
            Log.d("loadInfo", "result : $result")
            _artInfo.value = result.getOrNull()
        }
    }
}
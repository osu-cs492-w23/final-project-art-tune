package com.example.arttune.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arttune.api.SpotifyService
import com.example.arttune.data.LoadingStatus
import com.example.arttune.data.SpotifyTrack
import com.example.arttune.data.SpotifyTrackItemsJson
import com.example.arttune.data.SpotifyTracksRepository
import kotlinx.coroutines.launch

class SpotifySearchViewModel : ViewModel() {
    private val repository = SpotifyTracksRepository(SpotifyService.create())
    private val _searchResults = MutableLiveData<List<SpotifyTrack>?>(null)
    val searchResults: LiveData<List<SpotifyTrack>?> = _searchResults

    private val _loadingStatus = MutableLiveData<LoadingStatus>(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus
    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadSearch(q: String, key: String) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            _errorMessage.value = null
            Log.v("vm","query: $q")
            val result = repository.loadTracksSearch(q, key)
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
            _searchResults.value = result.getOrNull()
            _errorMessage.value = result.exceptionOrNull()?.message
        }
    }
}
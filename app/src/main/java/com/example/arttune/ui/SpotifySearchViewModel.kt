package com.example.arttune.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamratzman.spotify.SpotifyApiBuilder
import com.adamratzman.spotify.SpotifyAppApiBuilder
import com.adamratzman.spotify.endpoints.pub.SearchApi
import com.adamratzman.spotify.models.PagingObject
import com.adamratzman.spotify.models.Track
import com.example.arttune.api.SpotifyService
import com.example.arttune.data.LoadingStatus
import com.example.arttune.data.SpotifyTrack
import com.example.arttune.data.SpotifyTrackItemsJson
import com.example.arttune.data.SpotifyTracksRepository
import kotlinx.coroutines.launch

class SpotifySearchViewModel : ViewModel() {
    private val repository = SpotifyTracksRepository(SpotifyService.create())
    private val _searchResults = MutableLiveData<List<SpotifyTrackItemsJson>?>(null)
    val searchResults: LiveData<List<SpotifyTrackItemsJson>?> = _searchResults

    private val _loadingStatus = MutableLiveData<LoadingStatus>(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus
    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private var _apiResult : MutableList<List<String>> = mutableListOf(listOf())
    val apiResult : MutableList<List<String>> = _apiResult

    var searchApi : SearchApi? = null

    fun loadSearch(q: String) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            _errorMessage.value = null
            val result = repository.loadTracksSearch(q)
            val otherResult = searchApi?.searchTrack(q,5)
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
            _searchResults.value = result.getOrNull()
            _errorMessage.value = result.exceptionOrNull()?.message
        }
    }

    fun getTracks(q:String) :  MutableList<List<String>>{
        viewModelScope.launch {
            // get first 5 only.
            val result = searchApi?.searchTrack(q,5)
            cleanSpotifyresults(result)
        }
        return _apiResult
    }

    // This function makes the spotify api results human readable
    fun cleanSpotifyresults(result: PagingObject<Track>?){


        var fullResultSet: MutableList<List<String>> = mutableListOf(listOf())

        if (result != null) {
            for (t in result.items) {
                var singleResultSet = mutableListOf<String>()
                singleResultSet.add(t.artists[0].name)
                singleResultSet.add(t.name)

                // API returns track playback time in milliseconds so this
                // converts it to the traditional m:ss display style.
                val timeInSeconds = (t.length/1000).toInt()
                val minutes = (timeInSeconds/60).toInt()
                val remainderSeconds = (timeInSeconds%60).toInt()
                val timeString = "$minutes:$remainderSeconds"
                singleResultSet.add(timeString)

                singleResultSet.add(t.externalUrls.spotify.toString())

                fullResultSet.add(singleResultSet)
            }
        }
        _apiResult = fullResultSet
        Log.d("Spotify repo", "result of search is: ${fullResultSet}")
    }
    fun connect(){
        viewModelScope.launch {
            val result = repository.connectToApi()
            searchApi = result
        }
    }
}
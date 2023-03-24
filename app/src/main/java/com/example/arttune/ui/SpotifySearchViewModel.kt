package com.example.arttune.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamratzman.spotify.endpoints.pub.SearchApi
import com.adamratzman.spotify.models.PagingObject
import com.adamratzman.spotify.models.Track
import com.example.arttune.api.SpotifyService
import com.example.arttune.data.LoadingStatus
import com.example.arttune.data.SpotifyTrack
import com.example.arttune.data.SpotifyTracksRepository
import kotlinx.coroutines.launch
import kotlin.math.floor

class SpotifySearchViewModel : ViewModel() {
    private val repository = SpotifyTracksRepository(SpotifyService.create())
    private val _searchResults = MutableLiveData<List<SpotifyTrack>?>(null)
    val searchResults: LiveData<List<SpotifyTrack>?> = _searchResults

    private val _trackResults = MutableLiveData<PagingObject<Track>?>(null)
    val trackResults: LiveData<PagingObject<Track>?> = _trackResults

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

            Log.v("vm","query: $q")
            val result = searchApi?.searchTrack(q, 5)
            cleanSpotifyResults(result)
            Log.v("result", "$result")
            _loadingStatus.value = when (result.isNullOrEmpty()) {
                false -> LoadingStatus.SUCCESS
                true -> LoadingStatus.ERROR
            }
            _trackResults.value = result
            _errorMessage.value = result.isNullOrEmpty()?.toString()
        }
    }

    fun randomSearch() {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            _errorMessage.value = null

            var characters = "abcdefghijklmnopqrstuvwxyz"
            var randomCharacter = characters.get(floor(Math.random() * characters.length).toInt())
            var randomTerm = randomCharacter.toString()
            val result = searchApi?.searchTrack(randomTerm, 5)
            cleanSpotifyResults(result)
            _loadingStatus.value = when (result.isNullOrEmpty()) {
                false -> LoadingStatus.SUCCESS
                true -> LoadingStatus.ERROR
            }
            _trackResults.value = result
            _errorMessage.value = result.isNullOrEmpty()?.toString()
        }
    }

    fun getTracks(q:String) :  MutableList<List<String>>{
        viewModelScope.launch {
            // get first 5 only.
            val result = searchApi?.searchTrack(q,5)
            cleanSpotifyResults(result)
        }
        return _apiResult
    }

    // This function makes the spotify api results human readable
    fun cleanSpotifyResults(result: PagingObject<Track>?) {
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
        // Log.d("Spotify repo", "result of search is: $fullResultSet")
    }
    fun connect(){
        viewModelScope.launch {
            val result = repository.connectToApi()
            searchApi = result
        }
    }
}
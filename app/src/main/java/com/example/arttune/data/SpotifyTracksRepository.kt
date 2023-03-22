package com.example.arttune.data

import android.util.Log
import com.adamratzman.spotify.endpoints.pub.SearchApi
import com.adamratzman.spotify.spotifyAppApi
import com.example.arttune.api.SpotifyService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpotifyTracksRepository (
    private val service: SpotifyService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadTracksSearch(q: String) : Result<List<SpotifyTrackItemsJson>> =
        withContext(dispatcher) {
            try {
                val response = service.searchTrack(q)
                if (response.isSuccessful) {
                    Result.success(response.body()?.items ?: listOf())
                }
                else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    suspend fun connectToApi(): SearchApi {
        val api = spotifyAppApi("bd492cb109694c66bff3018f3d83254e", "9f02ab55a6bb4fdc8ab0e42a6208574a").build() // create and build api
        val search = api.search

        return search


    }
}
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
    suspend fun loadTracksSearch(q: String, key: String) : Result<List<SpotifyTrack>> =
        withContext(dispatcher) {
            Log.v("repo","query: $q")

            try {
                val response = service.searchTrack(q, "track")
                Log.d("response finished", "hello")
                if (response.isSuccessful) {
                    Log.v("load success", "response: ${response.body()?.items}")
                    Result.success(response.body()?.items ?: listOf())
                }
                else {
                    Log.v("load failure", "response: ${response.errorBody()}")
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun connectToApi(): SearchApi {
        val api = spotifyAppApi(
            "bd492cb109694c66bff3018f3d83254e",
            "9f02ab55a6bb4fdc8ab0e42a6208574a"
        ).build() // create and build api

        return api.search
    }
}


package com.example.arttune.data

import android.util.Log
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

    private fun buildSpotifyQuery(query: String, type: String) : String {
        var full = query
        full += type
        return full
    }
}


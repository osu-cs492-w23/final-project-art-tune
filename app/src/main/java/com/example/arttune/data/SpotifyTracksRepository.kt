package com.example.arttune.data

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
}
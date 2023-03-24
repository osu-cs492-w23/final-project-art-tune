package com.example.arttune.data

import android.util.Log
import com.adamratzman.spotify.endpoints.pub.SearchApi
import com.adamratzman.spotify.spotifyAppApi
import com.example.arttune.BuildConfig
import com.example.arttune.api.SpotifyService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val SPOTIFY_USER = BuildConfig.SPOTIFY_USER
const val SPOTIFY_APPID = BuildConfig.SPOTIFY_API_KEY

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
            SPOTIFY_USER,
            SPOTIFY_APPID
        ).build() // create and build api

        return api.search
    }
}


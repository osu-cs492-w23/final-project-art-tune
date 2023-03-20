package com.example.arttune.api

import com.example.arttune.data.TrackResults
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotifyService {

    @GET("search")
    suspend fun searchTrack(
        @Query("q") query: String,
        @Query("type") type: String = "track"
    ) : Response<TrackResults>

    companion object {
        private const val BASE_URL = "https://api.spotify.com/v1/"
        fun create() : SpotifyService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(SpotifyService::class.java)
        }
    }
}
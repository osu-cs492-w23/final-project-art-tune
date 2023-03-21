package com.example.arttune.api

import com.example.arttune.data.ArtResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChicagoArtService {
    @GET("artworks")
    suspend fun searchArt(
        @Query("id") id: String,
        @Query("title") title: String,
        @Query("artist_display") artist: String,
        @Query("date_display") date: String
    ) : Response<ArtResults>

    companion object {
        private const val BASE_URL = "https://api.artic.edu/api/v1/"
    }
}
package com.example.arttune.api

import com.example.arttune.data.ArtResults
import com.example.arttune.data.ArtWork
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChicagoArtService {
    @GET("search")
    suspend fun searchArt(
        @Query("q") query: String
    ) : Response<ArtResults>

    @GET("{id}")
    suspend fun getArtworkById(
        @Path("id") id: Int
    ) : Response<ArtWork>

    companion object {
        private const val BASE_URL = "https://api.artic.edu/api/v1/artworks/"
        fun create() : ChicagoArtService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ChicagoArtService::class.java)
        }
    }
}
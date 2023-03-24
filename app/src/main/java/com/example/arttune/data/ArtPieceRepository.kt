package com.example.arttune.data

import android.util.Log
import com.example.arttune.api.ChicagoArtService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtPieceRepository (
    private val service: ChicagoArtService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun loadArtSearch(q: String) : Result<List<ArtPiece>> =
        withContext(dispatcher) {
            try {
                val response = service.searchArt(q)
//                Log.d("Art Repo", "RESPONSE IS : ${response.body()?.artPieces}")
                if (response.isSuccessful) {
                    Result.success(response.body()?.artPieces ?: listOf())
                }
                else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun loadArtInfo(q: String) : Result<ArtWorkInfo> =
        withContext(dispatcher) {
            try {
                val response = service.getArtworkById(q.toInt())
                Log.d("art repo", "RESPONSE $response")
                if (response.isSuccessful) {
                    Result.success(response.body()!!.piece)
                }
                else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}
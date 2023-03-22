package com.example.arttune.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.api.SpotifyService
import com.example.arttune.R
import com.google.android.material.progressindicator.CircularProgressIndicator

const val SPOTIFY_KEY = "9f02ab55a6bb4fdc8ab0e42a6208574a"
const val ART_KEY = ""

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val spotifySearchViewModel: SpotifySearchViewModel by viewModels()
    private val artSearchViewModel : ChicagoArtViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temp = artSearchViewModel.loadSearch("cat")

        Log.d("Main","Temp is: ${artSearchViewModel.searchResults.value}")
    }
    override fun onResume() {
        super.onResume()
        /*
         * Read preferences here...
         */
    }


}
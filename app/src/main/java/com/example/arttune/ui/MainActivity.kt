package com.example.arttune.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.api.SpotifyService
import com.google.android.material.progressindicator.CircularProgressIndicator

const val SPOTIFY_KEY = "9f02ab55a6bb4fdc8ab0e42a6208574a"

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onResume() {
        super.onResume()
        /*
         * Read preferences here...
         */
    }


}
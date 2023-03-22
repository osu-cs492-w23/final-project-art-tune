package com.example.arttune.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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


        // THESE ARE FOR TESTING DELETE LATER
        val temp = artSearchViewModel.loadSearch("cat")
        artSearchViewModel.loadInfo("656")


    }
    override fun onResume() {
        super.onResume()

        // FOR TESTING DELETE LATER
        val temp = artSearchViewModel.loadSearch("cat")
        artSearchViewModel.loadInfo("656")
        Log.d("Main","Temp is: ${artSearchViewModel.searchResults.value}")
        Log.d("Main", "ART INFO IS:  ${artSearchViewModel.artInfo.value}")
        /*
         * Read preferences here...
         */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_gallery -> {
                val intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
}
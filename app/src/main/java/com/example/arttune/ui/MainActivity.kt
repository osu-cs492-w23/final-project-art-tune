package com.example.arttune.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adamratzman.spotify.spotifyAppApi
import com.example.arttune.api.SpotifyService
import com.example.arttune.R

const val SPOTIFY_KEY = "9f02ab55a6bb4fdc8ab0e42a6208574a"
const val ART_KEY = ""

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val spotifySearchViewModel: SpotifySearchViewModel by viewModels()
    private val artSearchViewModel : ChicagoArtViewModel by viewModels()
    private val trackListAdapter = TrackAdapter()
    private lateinit var searchResultsListRV: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBoxET: EditText = findViewById(R.id.et_search_box)
        val searchBtn: Button= findViewById(R.id.search_button)
        val randomBtn: Button = findViewById(R.id.randomize_button)

        searchResultsListRV = findViewById(R.id.rv_search_results)
        searchResultsListRV.layoutManager = LinearLayoutManager(this)
        searchResultsListRV.setHasFixedSize(true)
        searchResultsListRV.adapter = trackListAdapter

        spotifySearchViewModel.searchResults.observe(this) { searchResults ->
            trackListAdapter.updateTrackList(searchResults)
        }

        searchBtn.setOnClickListener {
            Log.v("main","search button")
            val query = searchBoxET.text.toString()
            if (!TextUtils.isEmpty(query)) {
                spotifySearchViewModel.loadSearch(query, SPOTIFY_KEY)
                Log.v("main","${spotifySearchViewModel.searchResults.value}")
                searchResultsListRV.scrollToPosition(0)
            }
        }

        // THESE ARE FOR TESTING DELETE LATER
        val temp = artSearchViewModel.loadSearch("cat")
        artSearchViewModel.loadInfo("656")
        spotifySearchViewModel.connect()

    }
    override fun onResume() {
        super.onResume()

        // FOR TESTING DELETE LATER
        val temp = artSearchViewModel.loadSearch("cat")
        artSearchViewModel.loadInfo("656")

        Log.d("Main","Temp is: ${artSearchViewModel.searchResults.value}")
        Log.d("Main", "ART INFO IS:  ${artSearchViewModel.artInfo.value}")


        // WORKS, SOMETIMES IS LATE TO GET RESULTS
        val temp1 = spotifySearchViewModel.getTracks("lover")
        Log.d("Main", "SEARCH SPOTIFY API RESULT : ${temp1}")

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
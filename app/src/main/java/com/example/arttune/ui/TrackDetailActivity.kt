package com.example.arttune.ui

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.adamratzman.spotify.models.Track
import com.bumptech.glide.Glide
import com.example.arttune.R
import com.example.arttune.data.SavedPiece

class TrackDetailActivity : AppCompatActivity() {
    companion object TrackObject {
        var track: Track? = null
    }

    private val artSearchViewModel : ChicagoArtViewModel by viewModels()
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: ImageButton
    private lateinit var pauseButton: ImageButton
    private lateinit var seekBar: SeekBar

    private var isSaved = false
    private val viewModel:SavedPiecesViewModel by viewModels()
    private var savedPiece: SavedPiece? = null
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spotify_track_detail)

        if (intent != null && track != null) {
            val ctx = applicationContext
            val coverArt = findViewById<ImageView>(R.id.iv_detail_track_cover)

            Glide.with(ctx).load(track!!.album.images[0].url).into(coverArt)

            findViewById<TextView>(R.id.tv_detail_track_title).text = track!!.name
            findViewById<TextView>(R.id.tv_detail_track_artist).text = track!!.artists[0].name

            val timeInSeconds = (track!!.length/1000)
            val minutes = (timeInSeconds/60)
            val remainderSeconds = (timeInSeconds%60)
            val timeString = String.format("%d:%02d", minutes, remainderSeconds)
            findViewById<TextView>(R.id.tv_detail_track_length).text = timeString
        }

        val url = track!!.previewUrl
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
        }

        playButton = findViewById(R.id.detail_play_button)
        pauseButton = findViewById(R.id.detail_pause_button)
        seekBar = findViewById(R.id.detail_seekbar)

        pauseButton.isEnabled = false

        playButton.setOnClickListener {
            Log.d("play", "clicked")
            mediaPlayer.start()
            pauseButton.isEnabled = true
            playButton.isEnabled = false
        }

        pauseButton.setOnClickListener {
            mediaPlayer.pause()
            pauseButton.isEnabled = false
            playButton.isEnabled = true
        }

        //Mostly placeholder
        if(url != null && globalInfo != null) {
            val ctx = applicationContext
            Log.e("detail activity", "$globalInfo")

            artSearchViewModel.loadSearch(track!!.name)
            artSearchViewModel.searchResults.observe(this) { searchResults ->
                id = searchResults!![0].id
            }

            val artistName = globalInfo!!.artist_title
            val artName = globalInfo!!.title
            findViewById<TextView>(R.id.tv_detail_art_title).text = artName
            findViewById<TextView>(R.id.tv_detail_art_artist).text = artistName
            findViewById<TextView>(R.id.tv_detail_art_date).text = globalInfo!!.date_display
            findViewById<TextView>(R.id.tv_detail_art_genre).text = globalInfo!!.medium_display

            //val uniqueId = "1adf2696-8489-499b-cad2-821d7fde4b33"
            val uniqueId = globalInfo!!.image_id
            val artUrl = "https://www.artic.edu/iiif/2/${uniqueId}/full/843,/0/default.jpg"

            val classicArt = findViewById<ImageView>(R.id.iv_detail_art)
            Glide.with(ctx).load(artUrl).into(classicArt)

            savedPiece = SavedPiece(
                track!!.name,
                track!!.artists[0].name,
                globalInfo!!.title,
                globalInfo!!.artist_title,
                artUrl,
                url
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.spotify_track_detail, menu)

        val saveAction =menu?.findItem(R.id.action_savePiece)
        viewModel.getPieceByName(savedPiece!!.songName).observe(this){savedPiece->
            when(savedPiece){
                null->{
                    isSaved = false
                    saveAction?.isChecked = false
                    saveAction?.icon = AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_action_bookmark_off
                    )
                }
                else->{
                    isSaved = true
                    saveAction?.isChecked = true
                    saveAction?.icon = AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_action_bookmark_on
                    )
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_gallery_detail->{
                val intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_savePiece->{
                toggleSave()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    private fun toggleSave(){
        if(savedPiece != null){
            when(isSaved){
                false->{
                    viewModel.addSavedPiece(savedPiece!!)
                }
                true->{
                    viewModel.removeSavedPiece(savedPiece!!)
                }
            }
        }
    }

//
//    /**
//     * This method adds a custom menu to the action bar for this activity.
//     */
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.activity_repo_detail, menu)
//        return true
//    }
//
//    /**
//     * This method handles clicks on actions in the action bar menu for this activity.
//     */
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId) {
//            R.id.action_view_on_web -> {
//                viewRepoOnWeb()
//                true
//            }
//            R.id.action_share -> {
//                shareRepo()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    /**
//     * This method constructs an implicit intent to open the device's browser to view the current
//     * repo on github.com using the URL associated with the repo.
//     */
//    private fun viewRepoOnWeb() {
//        if (repo != null) {
//            val intent = Uri.parse(repo!!.url).let {
//                Intent(Intent.ACTION_VIEW, it)
//            }
//
//            /*
//             * Make sure the device has an appropriate app to handle the implicit intent.  If it
//             * doesn't, display an error message in a Snackbar.
//             */
//            try {
//                startActivity(intent)
//            } catch (e: ActivityNotFoundException) {
//                Snackbar.make(
//                    findViewById(R.id.coordinator_layout),
//                    R.string.action_view_on_web_error,
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
//        }
//    }
//
//    /**
//     * This method opens the Android Sharesheet to allow the user to share information about the
//     * current GitHub repo.
//     */
//    private fun shareRepo() {
//        if (repo != null) {
//            val text = getString(R.string.share_text, repo!!.name, repo!!.url)
//            val intent: Intent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_TEXT, text)
//                type = "text/plain"
//            }
//            startActivity(Intent.createChooser(intent, null))
//        }
//    }
}
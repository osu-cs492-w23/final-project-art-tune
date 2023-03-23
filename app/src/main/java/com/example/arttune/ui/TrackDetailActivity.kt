package com.example.arttune.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.adamratzman.spotify.models.Track
import com.example.arttune.R
import com.example.arttune.data.SpotifyTrack

const val EXTRA_TRACK = ""

class TrackDetailActivity : AppCompatActivity() {
    private var track: Track? = null

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: ImageButton
    private lateinit var pauseButton: ImageButton
    private lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spotify_track_detail)

        if (intent != null && intent.hasExtra(EXTRA_TRACK)) {
            track = intent.getSerializableExtra(EXTRA_TRACK) as Track

            findViewById<TextView>(R.id.tv_detail_track_title).text = track!!.name
            findViewById<TextView>(R.id.tv_detail_track_artist).text = track!!.artists[0].name
            findViewById<TextView>(R.id.tv_detail_track_length).text = track!!.length.toString()

        }

/*        val url = track!!.previewUrl
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
        }*/

        playButton = findViewById(R.id.detail_play_button)
        pauseButton = findViewById(R.id.detail_pause_button)
        seekBar = findViewById(R.id.detail_seekbar)

        pauseButton.isEnabled = false

        playButton.setOnClickListener {
            //mediaPlayer.start()
            pauseButton.isEnabled = true
            playButton.isEnabled = false
        }

        pauseButton.setOnClickListener {
            //mediaPlayer.pause()
            pauseButton.isEnabled = false
            playButton.isEnabled = true
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
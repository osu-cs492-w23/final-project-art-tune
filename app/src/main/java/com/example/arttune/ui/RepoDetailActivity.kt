package com.example.arttune.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.arttune.R
import com.example.arttune.data.SpotifyTrack

const val EXTRA_GITHUB_REPO = "GITHUB_REPO"

class RepoDetailActivity : AppCompatActivity() {
    private var repo: SpotifyTrack? = null

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playButton: ImageButton
    private lateinit var pauseButton: ImageButton
    private lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spotify_track_detail)

        //mediaPlayer = MediaPlayer.create(this,"TODO: URI or https to stream media from")
        playButton = findViewById(R.id.detail_play_button)
        pauseButton = findViewById(R.id.detail_seekbar)
        seekBar = findViewById(R.id.detail_seekbar)

        pauseButton.isEnabled = false

        playButton.setOnClickListener {
            //mediaPlayer.start()
            pauseButton.isEnabled = true
            playButton.isEnabled = false
        }

        pauseButton.setOnClickListener {
            //mediaPlayer.pause();
            pauseButton.isEnabled = false
            playButton.isEnabled = true
        }//
//        /*
//         * If an intent was used to launch this activity and it contains information about a
//         * GitHub repo, use that information to populate the UI.
//         */
//        if (intent != null && intent.hasExtra(EXTRA_GITHUB_REPO)) {
//            repo = intent.getSerializableExtra(EXTRA_GITHUB_REPO) as SpotifyTrack
//
//            findViewById<TextView>(R.id.tv_repo_name).text = repo!!.name
//            findViewById<TextView>(R.id.tv_repo_stars).text = repo!!.stars.toString()
//            findViewById<TextView>(R.id.tv_repo_description).text = repo!!.description
//        }
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
package com.example.arttune.ui

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adamratzman.spotify.models.PagingObject
import com.adamratzman.spotify.models.Track
import com.example.arttune.R
import com.example.arttune.data.SpotifyTrack

class TrackAdapter(private val onTrackClick: (Track) -> Unit) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {
    private var tracks = listOf<Track>()

    override fun getItemCount() = tracks.size

    fun updateTrackList(newList: List<Track>?) {
        tracks = newList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.spotify_track_list_item, parent, false)
        return TrackViewHolder(itemView, onTrackClick)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    class TrackViewHolder (view: View, private val onClick: (Track) -> Unit) : RecyclerView.ViewHolder(view) {
        private var currentTrack: Track? = null
        private val nameTV = view.findViewById<TextView>(R.id.tv_track_title)
        private val artistTV = view.findViewById<TextView>(R.id.tv_track_artist)
        private val lengthTV = view.findViewById<TextView>(R.id.tv_track_length)
        private val artTV = view.findViewById<ImageView>(R.id.iv_track_image)
        private val uriTV = view.findViewById<TextView>(R.id.link)

        init {
            view.setOnClickListener {
                currentTrack?.let(onClick)
            }
        }

        fun bind(track: Track) {
            currentTrack = track
            artistTV.text = track.artists[0].name
            nameTV.text = track.name
            uriTV.text = track.externalUrls.spotify.toString()


            val timeInSeconds = (track.length/1000)
            val minutes = (timeInSeconds/60)
            val remainderSeconds = (timeInSeconds%60)
            val timeString = "$minutes:$remainderSeconds"
            lengthTV.text = timeString

            Log.v("track info", "${nameTV.text}, ${artistTV.text}, ${lengthTV.text}, ${uriTV.text}")
        }
    }
}
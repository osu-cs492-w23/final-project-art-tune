package com.example.arttune.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adamratzman.spotify.models.Track
import com.example.arttune.R
import com.example.arttune.data.SpotifyTrack

class TrackAdapter() : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {
    private var tracks = listOf<Track>()

    override fun getItemCount() = tracks.size

    fun updateTrackList(newList: List<Track>?) {
        tracks = newList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.spotify_track_list_item, parent, false)
        return TrackViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    class TrackViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private var currentTrack: Track? = null
        private val nameTV = view.findViewById<TextView>(R.id.tv_track_title)
        private val artistTV = view.findViewById<TextView>(R.id.tv_track_title)
        private val lengthTV = view.findViewById<TextView>(R.id.tv_track_length)

        fun bind(track: Track) {
            currentTrack = track
            nameTV.text = track.name
            artistTV.text = track.artists.toString()
            lengthTV.text = track.length.toString()
        }
    }
}
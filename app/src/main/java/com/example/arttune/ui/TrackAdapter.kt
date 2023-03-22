package com.example.arttune.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SpotifyTrack

class TrackAdapter() : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {
    private var tracks = listOf<SpotifyTrack>()

    override fun getItemCount() = tracks.size

    fun updateTrackList(newList: List<SpotifyTrack>?) {
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
        private var currentTrack: SpotifyTrack? = null
        private val nameTV = view.findViewById<TextView>(R.id.tv_track_title)
        private val artistTV = view.findViewById<TextView>(R.id.tv_track_title)
        private val lengthTV = view.findViewById<TextView>(R.id.tv_track_length)

        fun bind(track: SpotifyTrack) {
            currentTrack = track
            nameTV.text = track.trackTitle
            artistTV.text = track.artists
            lengthTV.text = track.length.toString()
        }
    }
}
package com.example.arttune.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SpotifyTrack

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {
    val tracks: MutableList<SpotifyTrack> = mutableListOf()

    override fun getItemCount() = tracks.size

    fun addTrack(track: SpotifyTrack) {
        tracks.add(0,track)
        notifyItemInserted(0)
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
        private val nameTV = view.findViewById<TextView>(R.id.tv_track_title)
        private var currentTrack: SpotifyTrack? = null

        fun bind(track: SpotifyTrack) {
            currentTrack = track
            nameTV.text = track.trackTitle
        }
    }
}
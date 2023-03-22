package com.example.arttune.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SavedPiece

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private var gallery: List<SavedPiece> = listOf()
    override fun getItemCount(): Int = this.gallery.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.gallery[position])
    }

    fun updateGallery(){
        gallery = listOf(SavedPiece("On GP", "Death Grips"))
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val songName: TextView = itemView.findViewById(R.id.tv_gallery_track_title)
        private val artistName: TextView = itemView.findViewById(R.id.tv_gallery_artist)

        fun bind(savedPiece: SavedPiece){
            songName.text = savedPiece.songName
            artistName.text = savedPiece.artist
        }
    }
}
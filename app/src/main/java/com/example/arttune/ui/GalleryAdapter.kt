package com.example.arttune.ui

import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        gallery = listOf(SavedPiece(listOf("On GP", "Death Grips"), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/1200px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg"))
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val songName: TextView = itemView.findViewById(R.id.tv_song_name)
        private val artistName: TextView = itemView.findViewById(R.id.tv_artist_name)
        private val art: ImageView = itemView.findViewById(R.id.iv_saved_art)

        fun bind(savedPiece: SavedPiece){
            val ctx = itemView.context
            songName.text = savedPiece.songArtist[0]
            artistName.text = savedPiece.songArtist[1]
            Glide.with(ctx).load(savedPiece.imgUrl).into(art)
        }
    }
}
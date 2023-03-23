package com.example.arttune.ui

import android.app.AlertDialog
import android.util.Log
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SavedPiece

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private var viewModel: SavedPiecesViewModel? = null
    private var gallery: MutableList<SavedPiece> = mutableListOf()
    override fun getItemCount(): Int = gallery.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gallery[position])

        holder.saveButton.setOnClickListener{
            if(viewModel != null) {
                val toDelete = gallery[position]
                val builder = AlertDialog.Builder(holder.itemView.context)
                builder.setMessage("Are you sure you want to delete your saved piece?")
                    .setCancelable(false)
                    .setPositiveButton("Yes"){_,_->
                        viewModel!!.removeSavedPiece(toDelete)
                        gallery.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    .setNegativeButton("No"){dialog,_->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }
    }

    fun updateGallery(savedPieces: List<SavedPiece>?){
        gallery = savedPieces?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

    fun getViewModel(savedViewModel: SavedPiecesViewModel){
        viewModel = savedViewModel
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val songName: TextView = itemView.findViewById(R.id.tv_song_name)
        private val artistName: TextView = itemView.findViewById(R.id.tv_artist_name)
        private val art: ImageView = itemView.findViewById(R.id.iv_saved_art)
        val saveButton: CheckBox = itemView.findViewById(R.id.iv_save_button)

        fun bind(savedPiece: SavedPiece){
            val ctx = itemView.context
            songName.text = savedPiece.songName
            artistName.text = savedPiece.artist
            Glide.with(ctx).load(savedPiece.imgUrl).into(art)
        }
    }
}
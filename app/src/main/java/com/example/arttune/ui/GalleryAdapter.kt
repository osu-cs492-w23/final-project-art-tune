package com.example.arttune.ui

import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SavedPiece
import java.net.URL

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

        holder.shareButton.setOnClickListener{
            holder.shareSavedPiece(gallery[position])
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
        val saveButton: ImageView = itemView.findViewById(R.id.iv_save_button)
        val shareButton: ImageView = itemView.findViewById(R.id.iv_share_button)
        private val ctx = itemView.context

        fun bind(savedPiece: SavedPiece){
            songName.text = savedPiece.songName
            artistName.text = savedPiece.artist
            Glide.with(ctx).load(savedPiece.imgUrl).into(art)
        }
        fun shareSavedPiece(savedPiece: SavedPiece){
            val shareText = "${savedPiece.songName}\n ${savedPiece.artist}"
            //val uri = Uri.parse(savedPiece.imgUrl)

            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
//            val intent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_TEXT, shareText)
//                putExtra(Intent.EXTRA_STREAM, uri)
//                type = "*/*"
//            }
            startActivity(ctx, Intent.createChooser(intent, null), null)
        }
    }
}
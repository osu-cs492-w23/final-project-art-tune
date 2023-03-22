package com.example.arttune.ui

import android.util.Log
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SavedPiece

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private var viewModel: SavedPiecesViewModel? = null
    private var gallery: List<SavedPiece> = listOf()
    //private var toBeDeleted: List<Boolean> = listOf()
    override fun getItemCount(): Int = gallery.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gallery[position])
    }

    fun updateGallery(savedPieces: List<SavedPiece>?){
        gallery = savedPieces ?: listOf()
        notifyDataSetChanged()
    }

    fun getViewModel(savedViewModel: SavedPiecesViewModel){
        viewModel = savedViewModel
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val songName: TextView = itemView.findViewById(R.id.tv_song_name)
        private val artistName: TextView = itemView.findViewById(R.id.tv_artist_name)
        private val art: ImageView = itemView.findViewById(R.id.iv_saved_art)
        private val saveButton: ImageView = itemView.findViewById(R.id.iv_save_button)

        init {
            saveButton.setOnClickListener{
                saveButton.setImageDrawable(
                    AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.ic_action_bookmark_off
                    )
                )
//                checkedOff = false
//                findPiece()
            }
        }

        fun bind(savedPiece: SavedPiece){
            val ctx = itemView.context
            songName.text = savedPiece.songName
            artistName.text = savedPiece.artist
            Glide.with(ctx).load(savedPiece.imgUrl).into(art)
        }
//        fun findPiece(){
//            val icon = saveButton.drawable
//            val drawa = AppCompatResources.getDrawable(
//                itemView.context,
//                R.drawable.ic_action_bookmark_off
//            )
//            //if(icon.equals(R.drawable.ic_action_bookmark_off))
//                Log.e("Debug", "Icon: $icon draw: $drawa")
//        }
    }
}
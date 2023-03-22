package com.example.arttune.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SavedPiece

class GalleryActivity: AppCompatActivity() {
    private lateinit var galleryRV: RecyclerView

    private val galleryAdapter = GalleryAdapter()
    private val viewModel: SavedPiecesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        supportActionBar?.title = "Gallery"
        galleryRV = findViewById(R.id.rv_gallery_list)
        galleryRV.layoutManager = LinearLayoutManager(this)
        galleryRV.setHasFixedSize(true)
        galleryRV.adapter =galleryAdapter

        val entry1 = SavedPiece(
            "In the Aeroplane Over the Sea", "Neutral Milk Hotel",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/ItAotS_postcard_origins.jpg/200px-ItAotS_postcard_origins.jpg"
        )
        val entry2 = SavedPiece(
            "Walkin", "Denzel Curry",
            "https://i.pinimg.com/originals/56/fe/97/56fe9724beef7c3e15f9b88928d18e3f.jpg"
        )
        viewModel.addSavedPiece(entry1)
        viewModel.addSavedPiece(entry2)

        //galleryAdapter.updateGallery()
        viewModel.savedPieces.observe(this){savedPieces ->
            galleryAdapter.updateGallery(savedPieces)
        }

    }
}
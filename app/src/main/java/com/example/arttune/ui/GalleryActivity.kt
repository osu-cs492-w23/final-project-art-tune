package com.example.arttune.ui

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.example.arttune.data.SavedPiece
import com.spotify.sdk.android.auth.LoginActivity.REQUEST_CODE
import java.util.jar.Manifest

class GalleryActivity: AppCompatActivity() {
    private lateinit var galleryRV: RecyclerView

    private val galleryAdapter = GalleryAdapter()
    private val viewModel: SavedPiecesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        //ActivityResultContracts.RequestPermission()
        ActivityCompat.requestPermissions(this,
            Array(1) {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
        REQUEST_CODE)

        supportActionBar?.title = "Gallery"
        galleryRV = findViewById(R.id.rv_gallery_list)
        galleryRV.layoutManager = LinearLayoutManager(this)
        galleryRV.setHasFixedSize(true)
        galleryRV.adapter = galleryAdapter
        galleryAdapter.getViewModel(viewModel)

        val entry1 = SavedPiece(
            "In the Aeroplane Over the Sea", "Neutral Milk Hotel",
            "Postcard", "Chris Bilheimer",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/ItAotS_postcard_origins.jpg/200px-ItAotS_postcard_origins.jpg",
            "placeholder"
        )
        val entry2 = SavedPiece(
            "Walkin", "Denzel Curry",
            "Young girl in a white dress walking on Skagen Sønderstrand",
            "Michael Peter Ancher",
            "https://i.pinimg.com/originals/56/fe/97/56fe9724beef7c3e15f9b88928d18e3f.jpg",
            "Placeholder"
        )
        viewModel.addSavedPiece(entry1)
        viewModel.addSavedPiece(entry2)
        viewModel.savedPieces.observe(this){savedPieces ->
            galleryAdapter.updateGallery(savedPieces)
        }
    }
}
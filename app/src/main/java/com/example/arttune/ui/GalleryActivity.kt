package com.example.arttune.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R
import com.spotify.sdk.android.auth.LoginActivity.REQUEST_CODE

class GalleryActivity: AppCompatActivity() {
    private lateinit var galleryRV: RecyclerView

    private val galleryAdapter = GalleryAdapter()
    private val viewModel: SavedPiecesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        ActivityCompat.requestPermissions(this,
            Array(1) {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
        REQUEST_CODE)

        supportActionBar?.title = "Gallery"
        galleryRV = findViewById(R.id.rv_gallery_list)
        galleryRV.layoutManager = LinearLayoutManager(this)
        galleryRV.setHasFixedSize(true)
        galleryRV.adapter = galleryAdapter
        galleryAdapter.getViewModel(viewModel)

        viewModel.savedPieces.observe(this){savedPieces ->
            galleryAdapter.updateGallery(savedPieces)
        }
    }
}
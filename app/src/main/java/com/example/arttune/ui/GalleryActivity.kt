package com.example.arttune.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arttune.R

class GalleryActivity: AppCompatActivity() {
    private lateinit var galleryRV: RecyclerView

    private val galleryAdapter = GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        supportActionBar?.title = "Gallery"
        galleryRV = findViewById(R.id.rv_gallery_list)
        galleryRV.layoutManager = LinearLayoutManager(this)
        galleryRV.setHasFixedSize(true)
        galleryRV.adapter =galleryAdapter

        galleryAdapter.updateGallery()

    }
}
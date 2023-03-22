package com.example.arttune.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Gallery"
    }
}
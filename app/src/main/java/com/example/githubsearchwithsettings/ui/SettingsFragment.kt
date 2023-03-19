package com.example.githubsearchwithsettings.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.githubsearchwithsettings.R

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}
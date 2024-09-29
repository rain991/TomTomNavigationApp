package com.savenko.tomtomnavigationapp.presentation

import androidx.fragment.app.FragmentManager
import com.savenko.tomtomnavigationapp.R
import com.tomtom.sdk.map.display.MapOptions
import com.tomtom.sdk.map.display.ui.MapFragment

fun createTomTomMapFragment(
    fragmentManager: FragmentManager,
    mapOptions: MapOptions,
    onFragmentReady: (MapFragment) -> Unit
) {
    val fragment = fragmentManager.findFragmentById(R.id.map_container) as MapFragment?
    val newFragment = fragment ?: MapFragment.newInstance(mapOptions)
    fragmentManager.beginTransaction()
        .add(newFragment, "map_fragment")
        .commitNow()
    onFragmentReady(newFragment)
}
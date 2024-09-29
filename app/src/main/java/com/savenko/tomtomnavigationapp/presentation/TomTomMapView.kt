package com.savenko.tomtomnavigationapp.presentation

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import com.savenko.tomtomnavigationapp.BuildConfig
import com.tomtom.sdk.location.GeoPoint

import com.tomtom.sdk.map.display.MapOptions
import com.tomtom.sdk.map.display.image.ImageFactory
import com.tomtom.sdk.map.display.marker.MarkerOptions
import com.tomtom.sdk.map.display.ui.MapFragment

@Composable
fun TomTomMapView(
    modifier: Modifier = Modifier
) {
    var mapFragment by remember {
        mutableStateOf<MapFragment?>(null)
    }
    var isMapFragmentReady by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val fragmentManager = (context as FragmentActivity).supportFragmentManager

    val mapOptions = MapOptions(
        mapKey = BuildConfig.TOMTOM_API_KEY,
    )
}
package com.savenko.tomtomnavigationapp.presentation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.savenko.tomtomnavigationapp.BuildConfig
import com.savenko.tomtomnavigationapp.R
import com.savenko.tomtomnavigationapp.data.navigation.areLocationPermissionsGranted
import com.tomtom.sdk.location.GeoLocation
import com.tomtom.sdk.location.GeoPoint
import com.tomtom.sdk.location.android.AndroidLocationProvider
import com.tomtom.sdk.location.mapmatched.MapMatchedLocationProvider
import com.tomtom.sdk.map.display.MapOptions
import com.tomtom.sdk.map.display.image.ImageFactory
import com.tomtom.sdk.map.display.location.LocationMarkerOptions
import com.tomtom.sdk.map.display.marker.MarkerOptions
import com.tomtom.sdk.map.display.ui.MapFragment
import com.tomtom.sdk.navigation.TomTomNavigation


@Composable
fun TomTomMapView(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val locationManager = AndroidLocationProvider(context = context)

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        locationManager.enable()
    }

    var mapFragment by remember {
        mutableStateOf<MapFragment?>(null)
    }
    var isMapFragmentReady by remember {
        mutableStateOf(false)
    }

    val fragmentManager = (context as FragmentActivity).supportFragmentManager

    val mapOptions = MapOptions(
        mapKey = BuildConfig.TOMTOM_API_KEY
    )

    locationManager.enable()

    LaunchedEffect(Unit) {
        createTomTomMapFragment(fragmentManager, mapOptions) { fragment ->
            mapFragment = fragment
            isMapFragmentReady = true
        }
    }
    Box(modifier) {
        if (isMapFragmentReady) {
            AndroidView(
                factory = {
                    mapFragment?.requireView() ?: View(it)
                },
                update = {
                    mapFragment?.getMapAsync { tomtomMap ->
                        tomtomMap.setLocationProvider(locationManager)
                        val locationMarkerOptions =
                            LocationMarkerOptions(
                                type = LocationMarkerOptions.Type.Chevron,
                            )
                        tomtomMap.enableLocationMarker(locationMarkerOptions)
                    }
                }
            )
        }
    }
}
package com.savenko.tomtomnavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.savenko.tomtomnavigationapp.data.navigation.LocationPermissionRequest
import com.savenko.tomtomnavigationapp.presentation.TomTomMapView
import com.savenko.tomtomnavigationapp.ui.theme.TomTomNavigationAppTheme

class TomTomNavigationActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TomTomNavigationAppTheme {
                LocationPermissionRequest()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TomTomMapView(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding))
                }
            }
        }
    }
}

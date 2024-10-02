package com.savenko.tomtomnavigationapp.data.navigation

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@Composable
fun LocationPermissionRequest() {
    val locationPermissionsState = areLocationPermissionsGranted()
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d("TAG", "MyComposable: granted perm. ")
        } else {
            Log.d("TAG", "MyComposable: perm. is not granted")
        }
    }

    LaunchedEffect(key1 = locationPermissionsState) {
      if(!locationPermissionsState){
          requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
          requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
      }
    }
}
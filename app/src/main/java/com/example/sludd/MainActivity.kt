package com.example.sludd

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.sludd.ui.screens.WeatherScreen
import com.example.sludd.ui.screens.WeatherViewModel
import com.example.sludd.ui.theme.SluddTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SluddTheme {
                KoinAndroidContext{
                    App()
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun App(weatherViewModel: WeatherViewModel = koinViewModel<WeatherViewModel>() ) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->

        val permissionsState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        var showRationale by rememberSaveable { mutableStateOf(false) }
        val permissionsGranted = rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(permissionsState) {
            permissionsState.launchMultiplePermissionRequest()
        }

        permissionsGranted.value = permissionsState.allPermissionsGranted
        if (permissionsState.shouldShowRationale) {
            showRationale = true
        }

        if (showRationale) {
            PermissionRationaleDialog(
                onDismiss = { showRationale = false },
                onConfirm = {
                    showRationale = false
                    permissionsState.launchMultiplePermissionRequest()
                }
            )
        }

        if (!permissionsGranted.value) {
            LaunchedEffect(Unit) {
                weatherViewModel.updateWeather()
            }
        } else {
            LaunchedEffect(Unit) {
                weatherViewModel.updateLocation()
                weatherViewModel.updateWeather()
            }
        }
        WeatherScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            weatherUiState = weatherViewModel.uiState
        )
    }
}

@Composable
fun PermissionRationaleDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.permission_required)) },
        text = { Text(stringResource(R.string.location_permission_required_long)) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Grant Permission")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

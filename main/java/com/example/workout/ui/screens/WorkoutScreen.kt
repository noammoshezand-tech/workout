package com.example.workout.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.workout.data.api.RetrofitInstance
import com.example.workout.data.repository.LocationRepository
import com.example.workout.data.repository.WeatherRepository
import com.example.workout.ui.components.ExerciseCard
import com.example.workout.ui.components.SnowAnimation
import com.example.workout.ui.components.WeatherDisplay
import com.example.workout.viewmodel.WorkoutViewModel
import com.google.android.gms.location.LocationServices

@Composable
fun WorkoutScreen() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    
    val viewModel = remember {
        val locationRepository = LocationRepository(fusedLocationClient)
        val weatherRepository = WeatherRepository(RetrofitInstance.api)
        WorkoutViewModel(weatherRepository, locationRepository)
    }

    var showWeather by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.loadWeatherForCurrentLocation()
            showWeather = true
        }
    }
    val weatherIcon = Icons.Default.Cloud

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(weatherIcon, contentDescription = "Weather")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ExerciseCard(
                exercise = viewModel.todayExercise,
                timeLeft = viewModel.timeLeft,
                isRunning = viewModel.isRunning,
                onStartClick = { viewModel.startWorkout() },
                onStopClick = { viewModel.stopWorkout() },
            )

            SnowAnimation(isRunning = viewModel.isRunning)

            AnimatedVisibility(
                visible = showWeather,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                        .clickable { showWeather = false },
                    contentAlignment = Alignment.Center
                ) {
                    viewModel.weatherInfo?.let { weather ->
                        WeatherDisplay(
                            weather = weather,
                            cityName = weather.name, // Displaying dynamic city name from API
                            modifier = Modifier
                                .padding(32.dp)
                                .clickable(enabled = false) {}
                        )
                    } ?: Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

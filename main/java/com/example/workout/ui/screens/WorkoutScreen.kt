package com.example.workout.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.workout.data.api.RetrofitInstance
import com.example.workout.data.repository.LocationRepository
import com.example.workout.data.repository.WeatherRepository
import com.example.workout.ui.components.ExerciseCard
import com.example.workout.ui.components.SnowAnimation
import com.example.workout.ui.components.WeatherDisplay
import com.example.workout.viewmodel.WorkoutViewModel
import com.example.workout.viewmodel.WorkoutPhase
import com.google.android.gms.location.LocationServices

@Composable
fun WorkoutScreen(navController: NavHostController) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val viewModel = remember {
        val locationRepository = LocationRepository(fusedLocationClient)
        val weatherRepository = WeatherRepository(RetrofitInstance.api)
        val databaseRepository = com.example.workout.data.repository.DataBaseRepository(context)
        WorkoutViewModel(weatherRepository, locationRepository , databaseRepository)
    }

    var showWeather by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isFineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val isCoarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        if (isFineGranted || isCoarseGranted) {
            viewModel.loadWeatherForCurrentLocation()
            showWeather = true
        }
    }
    val weatherIcon = Icons.Filled.Cloud

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(imageVector = weatherIcon, contentDescription = "Weather")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            WorkoutSummaryHeader(viewModel)

            Box(modifier = Modifier.weight(1f)) {
                when (viewModel.currentPhase) {
                    WorkoutPhase.FINISHED -> {
                        WorkoutCompletedScreen(onReset = { viewModel.resetToOverview() } , navController)
                    }

                    else -> {
                        val displayExercise = if (viewModel.currentPhase == WorkoutPhase.REST) {
                            com.example.workout.data.model.Exercize(
                                "Rest" ,
                                "Catch your breath",
                                viewModel.restDuration
                            )
                        } else {
                            viewModel.currentExercise
                        }

                        ExerciseCard(
                            exercise = displayExercise,
                            timeLeft = viewModel.timeLeft,
                            isRunning = viewModel.isRunning,
                            currentIndex = viewModel.currentExerciseIndex,
                            totalExercises = viewModel.todayExercises.size,
                            onStartClick = { viewModel.startWorkout() },
                            onStopClick = { viewModel.stopWorkout() }
                        )
                    }
                }

                SnowAnimation(isRunning = viewModel.isRunning)

                this@Column.AnimatedVisibility(
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
                                cityName = weather.name,
                                modifier = Modifier
                                    .padding(32.dp)
                                    .clickable(enabled = false) {}
                            )
                        } ?: Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp)
                                .background(
                                    MaterialTheme.colorScheme.surface,
                                    shape = MaterialTheme.shapes.medium
                                )
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
}

@Composable
fun WorkoutSummaryHeader(viewModel: WorkoutViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Today's Plan",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.todayExercises) { exercise ->
                    val isCurrent =
                        viewModel.currentExercise == exercise && viewModel.currentPhase == WorkoutPhase.EXERCISE
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCurrent) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier.widthIn(max = 120.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = exercise.name,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                                maxLines = 1
                            )
                            Text(
                                text = "${exercise.time}s",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutCompletedScreen(onReset: () -> Unit , navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color(0xFF4CAF50)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Workout Completed",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Great job! You've finished your daily workout.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("streakScreen") }) {
            Text("Go to Streak Screen")
        }

    }
}

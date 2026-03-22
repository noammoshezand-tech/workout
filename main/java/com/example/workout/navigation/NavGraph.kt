package com.example.workout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workout.ui.screens.WorkoutScreen
import com.example.workout.ui.screens.streakScreen

@Composable
fun WorkoutApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "workoutScreen") {
        composable("workoutScreen") {
            WorkoutScreen(navController = navController)
        }
        composable("streakScreen") {
            streakScreen(navController = navController)
        }
    }
}


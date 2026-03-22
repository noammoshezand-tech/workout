package com.example.workout

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workout.navigation.WorkoutApp
import com.example.workout.ui.screens.WorkoutScreen
import com.example.workout.ui.screens.streakScreen
import com.example.workout.service.OutMusicService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WorkoutApp()
        }
    }
    override fun onStart() {
        super.onStart()
        val intent = Intent(this, OutMusicService::class.java)
        stopService(intent)
    }

    override fun onStop() {
        super.onStop()
        val intent = Intent(this, OutMusicService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, OutMusicService::class.java)
        stopService(intent)
    }
}



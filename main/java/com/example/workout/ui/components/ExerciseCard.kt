package com.example.workout.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workout.data.model.Exercize

@Composable
fun ExerciseCard(
    exercise: Exercize,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit,
    isRunning: Boolean,
    timeLeft: Int
) {
    val progress = if (exercise.time > 0) {
        timeLeft / exercise.time.toFloat()
    } else 0f

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        label = "timerProgress"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Header Section
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = exercise.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Circular Timer Section
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(280.dp)
        ) {
            // Background Circle
            CircularProgressIndicator(
                progress = 1f,
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 12.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                strokeCap = StrokeCap.Round
            )
            
            // Progress Circle
            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 12.dp,
                color = MaterialTheme.colorScheme.primary,
                strokeCap = StrokeCap.Round
            )
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val minutes = timeLeft / 60
                val seconds = timeLeft % 60
                Text(
                    text = String.format("%02d:%02d", minutes, seconds),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Light
                    )
                )
                Text(
                    text = "REMAINING",
                    style = MaterialTheme.typography.labelMedium,
                    letterSpacing = 2.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // Control Button
        FilledIconButton(
            onClick = { if (isRunning) onStopClick() else onStartClick() },
            modifier = Modifier.size(100.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = if (isRunning) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = if (isRunning) Icons.Default.Refresh else Icons.Default.PlayArrow,
                contentDescription = if (isRunning) "Stop" else "Start",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}
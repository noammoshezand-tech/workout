package com.example.workout.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.*
import com.example.workout.R

@Composable
fun SnowAnimation(isRunning: Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.snowflakes))
    
    if (isRunning) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
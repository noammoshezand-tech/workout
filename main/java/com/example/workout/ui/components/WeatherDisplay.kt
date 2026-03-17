package com.example.workout.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.workout.data.model.WeatherResponse

@Composable
fun WeatherDisplay(
    weather: WeatherResponse,
    cityName: String,
    modifier: Modifier = Modifier
) {
    val condition = weather.weather.firstOrNull()?.main ?: ""
    val isClear = condition.contains("Clear", ignoreCase = true)
    
    val icon = if (isClear) Icons.Default.WbSunny else Icons.Default.Cloud
    val iconColor = if (isClear) Color(0xFFFFD700) else Color.White

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4A90E2),
                        Color(0xFF50E3C2)
                    )
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = cityName,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            imageVector = icon,
            contentDescription = condition,
            modifier = Modifier.size(72.dp),
            tint = iconColor
        )

        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "${weather.main.temp.toInt()}°C",
            style = MaterialTheme.typography.displayLarge,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        
        Text(
            text = weather.weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: "",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White.copy(alpha = 0.8f)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WeatherDetailItem(label = "Humidity", value = "${weather.main.humidity}%")
            WeatherDetailItem(label = "Condition", value = condition)
        }
    }
}

@Composable
fun WeatherDetailItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.7f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

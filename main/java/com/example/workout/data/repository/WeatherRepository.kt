package com.example.workout.data.repository

import com.example.workout.data.api.WeatherApi
import com.example.workout.data.model.WeatherResponse

class WeatherRepository(private val api: WeatherApi) {
        suspend fun getWeather(lat : Double, lon : Double): WeatherResponse {
            return api.getWeather(
                lat,
                lon,
                "cf2881d7299801eb6da93362eb6f1778",
                "metric"
            )
        }

    }
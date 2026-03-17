package com.example.workout.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.data.api.RetrofitInstance
import com.example.workout.data.model.Exercize
import com.example.workout.data.model.WeatherResponse
import com.example.workout.data.repository.LocationRepository
import com.example.workout.data.repository.WeatherRepository
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorkoutViewModel(private val weatherRepository: WeatherRepository,
                       private val locationRepository: LocationRepository) : ViewModel() {

    private var timerJob: Job? = null
    var weatherInfo by mutableStateOf<WeatherResponse?>(null)
        private set
    var timeLeft by mutableIntStateOf(0)
        private set
    var isRunning by mutableStateOf(false)
        private set



    private val exercisesByDay = mapOf(
        1 to Exercize("Push Ups", "Chest exercise", 20),
        2 to Exercize("Squats", "Leg exercise", 25),
        3 to Exercize("Plank", "Core exercise", 25),
        4 to Exercize("Lunges", "Leg exercise", 25),
        5 to Exercize("Burpees", "Full body", 25),
        6 to Exercize("Sit Ups", "Abs exercise", 25),
        7 to Exercize("Yoga", "Stretch and relax", 25)
    )

    val todayExercise: Exercize
        get() {
            val dayOfWeek = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)
            return exercisesByDay[dayOfWeek] ?: exercisesByDay[1]!!
        }

    fun startWorkout() {
        if (isRunning) return
        isRunning = true
        timeLeft = todayExercise.time
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (timeLeft > 0 && isRunning) {
                delay(1000)
                timeLeft--
            }
            isRunning = false
        }
    }

    fun stopWorkout() {
        isRunning = false
        timerJob?.cancel()
    }

    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                weatherInfo = weatherRepository.getWeather(lat, lon)
            } catch (e: Exception) {
            }
        }
    }
    fun loadWeatherForCurrentLocation() {
        viewModelScope.launch @androidx.annotation.RequiresPermission(allOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION]) {
            val location = locationRepository.getNetworkLocation()
            location?.let { (lat, lon) ->
                loadWeather(lat, lon)
            }
        }
    }
}

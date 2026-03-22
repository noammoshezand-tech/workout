package com.example.workout.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.data.database.CompletedExercise
import com.example.workout.data.model.Exercize
import com.example.workout.data.model.WeatherResponse
import com.example.workout.data.repository.DataBaseRepository
import com.example.workout.data.repository.LocationRepository
import com.example.workout.data.repository.WeatherRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

enum class WorkoutPhase {
    OVERVIEW, EXERCISE, REST, FINISHED
}

class WorkoutViewModel(private val weatherRepository: WeatherRepository,
                       private val locationRepository: LocationRepository,
                       private val databaseRepository: DataBaseRepository
) : ViewModel() {

    private var timerJob: Job? = null
    var weatherInfo by mutableStateOf<WeatherResponse?>(null)
        private set
    var timeLeft by mutableIntStateOf(0)
        private set
    var isRunning by mutableStateOf(false)
        private set
    var currentPhase by mutableStateOf(WorkoutPhase.OVERVIEW)
        private set

    val restDuration = 1

    private val exercisesByDay = mapOf(
        Calendar.SUNDAY to listOf(
            Exercize("Pull Ups", "Back exercise - Wide grip", 1),
            Exercize("Dumbbell Rows", "Back exercise - Rows", 1),
            Exercize("Cable Pullover", "Back exercise - Cables", 1)
        ),
        Calendar.MONDAY to listOf(
            Exercize("Push Ups", "Chest exercise - Standard", 1),
            Exercize("Bench Press", "Chest exercise - Dumbbells", 1),
            Exercize("Chest Flyes", "Chest exercise - Machine", 1)
        ),
        Calendar.TUESDAY to listOf(
            Exercize("Deadlift", "Back exercise - Strength", 1),
            Exercize("T-Bar Row", "Back exercise", 1),
            Exercize("Lat Pulldown", "Back exercise - Wide", 1)
        ),
        Calendar.WEDNESDAY to listOf(
            Exercize("Plank", "Abs exercise - Static", 1),
            Exercize("Crunches", "Abs exercise - Standard", 1),
            Exercize("Leg Raises", "Abs exercise - Hanging", 1)
        ),
        Calendar.THURSDAY to listOf(
            Exercize("Squats", "Leg exercise", 1),
            Exercize("Lunges", "Leg exercise", 1),
            Exercize("Leg Extensions", "Leg exercise", 1)
        ),
        Calendar.FRIDAY to listOf(
            Exercize("Shoulder Press", "Shoulder exercise", 1),
            Exercize("Lateral Raises", "Shoulder exercise", 1),
            Exercize("Shrugs", "Shoulder exercise", 1)
        ),
        Calendar.SATURDAY to listOf(
            Exercize("Yoga Stretch", "Relaxation", 1),
            Exercize("Child Pose", "Restorative", 1),
            Exercize("Cat Cow", "Mobility", 1)
        )
    )

    var currentExerciseIndex by mutableIntStateOf(0)
        private set

    val todayExercises: List<Exercize>
        get() {
            val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            return exercisesByDay[dayOfWeek] ?: exercisesByDay[Calendar.SUNDAY]!!
        }

    val currentExercise: Exercize
        get() = todayExercises.getOrElse(currentExerciseIndex) { todayExercises[0] }

    init {
        timeLeft = currentExercise.time
        checkIfAlreadyFinishedToday()
    }

    private fun checkIfAlreadyFinishedToday() {
        viewModelScope.launch {
            val today = databaseRepository.getCurrentDate()
            val workout = databaseRepository.getWorkoutByDate(today)
            if (workout != null && workout.isCompleted) {
                currentPhase = WorkoutPhase.FINISHED
            }
        }
    }

    fun startWorkout() {
        if (currentPhase == WorkoutPhase.FINISHED) return

        if (currentPhase == WorkoutPhase.OVERVIEW) {
            currentExerciseIndex = 0
            currentPhase = WorkoutPhase.EXERCISE
            timeLeft = currentExercise.time
        }
        
        if (isRunning) return
        isRunning = true
        
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (isRunning) {
                if (timeLeft > 0) {
                    delay(1000)
                    timeLeft--
                } else {
                    handlePhaseTransition()
                }
            }
        }
    }

    private fun handlePhaseTransition() {
        when (currentPhase) {
            WorkoutPhase.EXERCISE -> {
                if (currentExerciseIndex < todayExercises.size - 1) {
                    currentPhase = WorkoutPhase.REST
                    timeLeft = restDuration
                } else {
                    currentPhase = WorkoutPhase.FINISHED
                    isRunning = false
                    markAsFinished()
                }
            }
            WorkoutPhase.REST -> {
                currentExerciseIndex++
                currentPhase = WorkoutPhase.EXERCISE
                timeLeft = currentExercise.time
            }
            else -> {
                isRunning = false
            }
        }
    }

    fun stopWorkout() {
        isRunning = false
        timerJob?.cancel()
    }

    fun resetToOverview() {
        viewModelScope.launch {
            val today = databaseRepository.getCurrentDate()
            val workout = databaseRepository.getWorkoutByDate(today)
            if (workout != null && workout.isCompleted) {
                currentPhase = WorkoutPhase.FINISHED
            } else {
                stopWorkout()
                currentPhase = WorkoutPhase.OVERVIEW
                currentExerciseIndex = 0
                timeLeft = currentExercise.time
            }
        }
    }

    fun loadWeather(lat: Double, lon: Double) {
        Log.d("WeatherDebug", "loadWeather called with lat: $lat, lon: $lon")
        viewModelScope.launch {
            try {
                weatherInfo = weatherRepository.getWeather(lat, lon)
                if (weatherInfo != null) {
                    Log.d("WeatherDebug", "Weather loaded successfully: ${weatherInfo?.name}")
                } else {
                    Log.e("WeatherDebug", "Weather response is null (API issue?)")
                }
            } catch (e: Exception) {
                Log.e("WeatherDebug", "Error fetching weather (Internet or API key issue): ${e.message}", e)
            }
        }
    }

    fun loadWeatherForCurrentLocation() {
        Log.d("WeatherDebug", "loadWeatherForCurrentLocation started")
        viewModelScope.launch @androidx.annotation.RequiresPermission(allOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION]) {
            try {
                val location = locationRepository.getNetworkLocation()
                if (location != null) {
                    val (lat, lon) = location
                    Log.d("WeatherDebug", "Location obtained: lat=$lat, lon=$lon")
                    loadWeather(lat, lon)
                } else {
                    Log.e("WeatherDebug", "Failed to get location (Permissions granted but GPS/Network location failed)")
                }
            } catch (e: SecurityException) {
                Log.e("WeatherDebug", "Permission error: ${e.message}")
            } catch (e: Exception) {
                Log.e("WeatherDebug", "Unknown error getting location: ${e.message}", e)
            }
        }
    }
    fun markAsFinished() {
        viewModelScope.launch {
            val today = databaseRepository.getCurrentDate()

            val exercise = CompletedExercise(
                date = today,
                isCompleted = true
            )
            databaseRepository.insertOrUpdateWorkout(exercise)
            Log.d("DatabaseDebug", "Workout marked as finished: $exercise")
        }
    }
}

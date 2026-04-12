package com.example.workout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.data.database.CompletedExercise
import com.example.workout.data.repository.DataBaseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.*

data class DayProgress(
    val dayName: String,
    val isCompleted: Boolean
)

class StreakViewModel(private val databaseRepository: DataBaseRepository) : ViewModel() {

    private val workoutHistory = databaseRepository.getAllWorkouts()

    val last7DaysProgress: StateFlow<List<DayProgress>> = workoutHistory.map { history: List<CompletedExercise> ->
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dayFormat = SimpleDateFormat("E", Locale.US)
        val calendar = Calendar.getInstance()
        
        val days = mutableListOf<DayProgress>()
        for (i in 0 until 7) {
            val dateStr = sdf.format(calendar.time)
            val dayName = dayFormat.format(calendar.time)
            val isCompleted = history.any { it.date == dateStr && it.isCompleted }
            days.add(DayProgress(dayName, isCompleted))
            calendar.add(Calendar.DAY_OF_YEAR, -1)
        }
        days.reversed()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val currentStreak: StateFlow<Int> = workoutHistory.map { history: List<CompletedExercise> ->
        calculateStreak(history)
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0)

    private fun calculateStreak(history: List<CompletedExercise>): Int {
        if (history.isEmpty()) return 0
        val sortedHistory = history.filter { it.isCompleted }.map { it.date }.distinct().sortedDescending()
        if (sortedHistory.isEmpty()) return 0

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val today = Calendar.getInstance()
        
        var streak = 0
        val calendar = Calendar.getInstance()
        
        val latestDateStr = sortedHistory[0]
        val latestDate = sdf.parse(latestDateStr) ?: return 0
        calendar.time = latestDate
        
        val diff = ((today.timeInMillis - calendar.timeInMillis) / (24 * 60 * 60 * 1000)).toInt()
        if (diff > 1) return 0

        for (i in sortedHistory.indices) {
            val dateStr = sortedHistory[i]
            val date = sdf.parse(dateStr) ?: break
            
            if (i == 0) {
                streak++
                calendar.time = date
            } else {
                calendar.add(Calendar.DAY_OF_YEAR, -1)
                val expectedDateStr = sdf.format(calendar.time)
                if (dateStr == expectedDateStr) {
                    streak++
                } else {
                    break
                }
            }
        }
        return streak
    }
}

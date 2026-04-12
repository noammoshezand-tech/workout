package com.example.workout.data.repository

import android.content.Context
import com.example.workout.data.database.AppDatabase
import com.example.workout.data.database.CompletedExercise
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DataBaseRepository(context: Context) {

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
    
    private val db = AppDatabase.getDatabase(context).completedExerciseDao()
    
    suspend fun insertOrUpdateWorkout(completedExercise: CompletedExercise) {
        db.insertOrUpdateWorkout(completedExercise)
    }

    suspend fun getWorkoutByDate(date: String): CompletedExercise? {
        return db.getWorkoutByDate(date)
    }

    fun getAllWorkouts(): Flow<List<CompletedExercise>> {
        return db.getAllWorkouts()
    }
}

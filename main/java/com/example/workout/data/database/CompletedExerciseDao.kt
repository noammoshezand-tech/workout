package com.example.workout.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateWorkout(completedExercise: CompletedExercise)

    @Query("SELECT * FROM workout_history WHERE date = :date")
    suspend fun getWorkoutByDate(date: String): CompletedExercise?

    @Query("SELECT * FROM workout_history")
    fun getAllWorkouts(): Flow<List<CompletedExercise>>
}
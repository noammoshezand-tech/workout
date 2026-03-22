package com.example.workout.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_history")
data class CompletedExercise(
    @PrimaryKey
    val date: String,
    val isCompleted: Boolean
)
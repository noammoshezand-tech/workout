package com.example.workout.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_exercises")
data class CompletedExercise(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val exerciseName: String,

    val description: String,

    val duration: Int,

    val date: Long
)
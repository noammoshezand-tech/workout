package com.example.workout.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CompletedExercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun completedExerciseDao(): CompletedExerciseDao
    companion object {
        const val DATABASE_NAME = "workout_database"
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
            }

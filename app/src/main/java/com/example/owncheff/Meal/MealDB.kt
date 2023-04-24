package com.example.owncheff.Meal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.owncheff.MealDao

@Database(entities = [MealEntity::class], version = 1)
    abstract class mealDB : RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: mealDB? = null
        fun getInstance(context: Context): mealDB =
            INSTANCE ?: synchronized(this)
            {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                mealDB::class.java, "Meal-db"
            ).build()
    }
}
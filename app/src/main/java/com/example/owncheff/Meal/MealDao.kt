package com.example.owncheff

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.owncheff.Meal.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meal_table")
    fun getAll(): Flow<List<MealEntity>>
    @Insert
    fun insertOne(meal: MealEntity)
    @Insert
    fun insertAll(meal: List<MealEntity>)
    @Query("DELETE FROM meal_table")
    fun deleteOne()
    @Query("DELETE FROM meal_table")
    fun deleteAll()
}

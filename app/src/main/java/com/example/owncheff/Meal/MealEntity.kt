package com.example.owncheff.Meal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


    @Entity(tableName = "meal_table")
    data class MealEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "Recipe Name") val recipe_name: String?,
        @ColumnInfo(name = "Recipe Instructions") val recipe_instructions: String?,
        //NOTE: just doing password string comparisons for now?
        @ColumnInfo(name = "Recipe Image") val recipe_image: String?,
    )

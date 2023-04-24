package com.example.owncheff.Meal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meal_table")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "Recipe_name") val recipe_name: String?,
)

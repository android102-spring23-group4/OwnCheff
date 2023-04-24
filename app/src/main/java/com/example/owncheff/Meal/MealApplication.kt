package com.example.owncheff.Meal

import android.app.Application

class MealApplication :Application() {
    val data by lazy {
        mealDB.getInstance(this)
    }
}
package com.example.owncheff

import android.app.Application
import com.example.owncheff.Meal.mealDB

class OwnCheffApp : Application() {
    val database by lazy { AppDB.getInstance(this) }
    val data by lazy {
        mealDB.getInstance(this)
    }
}
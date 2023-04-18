package com.example.owncheff

import android.app.Application

class OwnCheffApp : Application() {
    val database by lazy { AppDB.getInstance(this) }
}
package com.example.owncheff


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDB : RoomDatabase()
{
    abstract fun userDao(): UserDao
    companion object
    {
        @Volatile
        private var INSTANCE: AppDB? = null
        fun getInstance(context: Context): AppDB =
            INSTANCE ?: synchronized(this)
            {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDB::class.java, "Articles-db"
            ).build()
    }
}
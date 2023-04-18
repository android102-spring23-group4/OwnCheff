package com.example.owncheff

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): Flow<List<UserEntity>>
    @Insert
    fun insertOne(users: UserEntity)
    @Insert
    fun insertAll(users: List<UserEntity>)
    @Query("DELETE FROM user_table")
    fun deleteOne()
    @Query("DELETE FROM user_table")
    fun deleteAll()
}


package com.example.owncheff

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob


enum class UserRoles(val id: Long) {
    USER(2),
    MODERATOR(1),
    ADMIN(0)
}
@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "email") val email: String?,
    //NOTE: just doing password string comparisons for now?
    @ColumnInfo(name = "password") val password: String?, //TODO: encrypt as a AES-256bit hash
    @ColumnInfo(name = "role") val role: Long?,
    //@ColumnInfo(name = "recipe_list_id") val recipeListId: Long?,
    //@ColumnInfo(name = "settings") val settings: Blob?
)
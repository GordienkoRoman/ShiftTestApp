package com.example.shifttestapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shifttestapp.data.local.entities.UserEntity

@Dao
interface UsersDao {

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteUsers()


    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>
}
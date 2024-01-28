package com.example.shifttestapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.shifttestapp.data.local.entities.UserEntity

@Dao
interface UsersDao {
    @Upsert(entity = UserEntity::class)
    fun upsertUser(userEntity: UserEntity)


    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>
}
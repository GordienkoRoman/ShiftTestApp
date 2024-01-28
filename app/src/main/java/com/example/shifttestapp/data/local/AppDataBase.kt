package com.example.shifttestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shifttestapp.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract val usersDao: UsersDao
}
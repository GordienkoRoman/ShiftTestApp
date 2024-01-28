package com.example.shifttestapp.domain.model

import android.content.Context
import com.example.shifttestapp.data.local.UsersDao
import com.example.shifttestapp.data.local.entities.UserEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val usersDao: UsersDao,
    val context: Context
) {
    fun getUsers(): List<User> {
        return usersDao.getUsers().map { it.toUser() }
    }
}
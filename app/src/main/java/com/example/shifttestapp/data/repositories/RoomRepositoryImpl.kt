package com.example.shifttestapp.data.repositories

import android.content.Context
import com.example.shifttestapp.data.local.dao.UsersDao
import com.example.shifttestapp.data.mapper.toEntity
import com.example.shifttestapp.domain.model.User
import com.example.shifttestapp.domain.repositories.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl  @Inject constructor(
    private val usersDao: UsersDao,
    val context: Context
): RoomRepository {
    override suspend fun addUser(users: List<User>) {
        for (user in users)
      usersDao.insertUser(user.toEntity())
    }

    override suspend fun deleteUsers() {

    }

    override suspend fun getUsers(): List<User> {
       return listOf()
    }
}
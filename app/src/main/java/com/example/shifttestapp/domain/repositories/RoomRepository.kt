package com.example.shifttestapp.domain.repositories

import com.example.shifttestapp.domain.model.User

interface RoomRepository {
    suspend fun addUser(users: List<User>)

    suspend fun deleteUsers()

    suspend fun getUsers():List<User>
}
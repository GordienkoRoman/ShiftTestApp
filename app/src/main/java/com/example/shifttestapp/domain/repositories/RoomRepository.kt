package com.example.shifttestapp.domain.repositories

import com.example.shifttestapp.domain.model.User

interface RoomRepository {
    suspend fun addUsers(users: List<User>)

    suspend fun getUsers():List<User>
}
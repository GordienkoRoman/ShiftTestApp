package com.example.shifttestapp.presentation.userList

import com.example.shifttestapp.domain.model.User

sealed class UserListScreenState {

    object Initial : UserListScreenState()

    object Loading : UserListScreenState()

    data class Users(
        val users: List<User>
    ) : UserListScreenState()
}
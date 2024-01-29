package com.example.shifttestapp.presentation.userList

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shifttestapp.data.remote.RandomUserApi
import com.example.shifttestapp.domain.model.User
import com.example.shifttestapp.domain.repositories.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val randomUserApi: RandomUserApi
) : ViewModel() {
    val screenState = MutableStateFlow(UserListScreenState.Initial as UserListScreenState)
    fun loadUsers(context: Context) {
        viewModelScope.launch {
            screenState.emit(UserListScreenState.Loading)
            val users: List<User>
            try {
                users = roomRepository.getUsers()
            } catch (e: Exception) {
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
                fetchUsers(context)
                return@launch
            }
            if (users.isEmpty()) {
                fetchUsers(context)
            } else {
                screenState.emit(UserListScreenState.Users(users))
            }
        }
    }

    fun fetchUsers(context: Context) {
        viewModelScope.launch {
            runCatching {
                randomUserApi.getUsers()
            }.onSuccess {
                screenState.emit(
                    UserListScreenState.Users(
                        it.results.map { result -> User.mapToUser(result) })
                )
                roomRepository.addUsers(it.results.map { result -> User.mapToUser(result) })
            }.onFailure {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
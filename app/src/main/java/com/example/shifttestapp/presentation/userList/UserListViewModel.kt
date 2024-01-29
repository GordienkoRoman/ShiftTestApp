package com.example.shifttestapp.presentation.userList

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shifttestapp.data.remote.RandomUserApi
import com.example.shifttestapp.domain.model.User
import com.example.shifttestapp.domain.repositories.RoomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    val roomRepository: RoomRepository,
    private val randomUserApi: RandomUserApi,
    context: Context
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val loadingMutableStateFlow = MutableStateFlow(true)
    val loadingFlow
        get() = loadingMutableStateFlow.asStateFlow()

    private val remoteRandomUsersMutableStateFlow =
        MutableStateFlow<List<User>?>(null)
    val remoteRandomUsersStateFlow: Flow<List<User>?>
        get() = remoteRandomUsersMutableStateFlow.asStateFlow()

    val screenState : Flow<UserListScreenState> = loadUsers().map { UserListScreenState.Users(it) }
    fun loadUsers(): StateFlow<List<User>> = flow {
        val users = roomRepository.getUsers()
        emit(users)
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )
    fun fetchUsers(){
        viewModelScope.launch {
            loadingMutableStateFlow.value = true
            runCatching {
                randomUserApi.getUsers()
            }.onSuccess {
                Log.d("TAG",it.toString())
                remoteRandomUsersMutableStateFlow.value =
                    it.results.map { result -> User.mapToUser(result) }
                roomRepository.addUser(it.results.map { result -> User.mapToUser(result) })
                loadingMutableStateFlow.value = false
            }.onFailure {
                Log.d("TAG",it.message.toString())
                loadingMutableStateFlow.value = false
            }
        }
    }
}
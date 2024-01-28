package com.example.shifttestapp.presentation.userList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shifttestapp.di.viewModelFactory.ViewModelFactory
import com.example.shifttestapp.domain.model.User
import stud.gilmon.base.utils.launchAndCollectIn

@Composable
fun UserListScreem(factory: ViewModelFactory){
    val viewModel: UserListViewModel = viewModel(factory = factory)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val screenState = viewModel.screenState.collectAsState(UserListScreenState.Initial)
    val currentState = screenState.value
    val users = remember { mutableStateOf(listOf(User())) }
    if(currentState is UserListScreenState.Users)
    {
        if(currentState.users.isNotEmpty()) {

        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.requestUsers()
    }
    SideEffect {
//        viewModel.remoteRandomUsersStateFlow.launchAndCollectIn(lifecycleOwner.value) {
//            if (it != null)
//                users.value = it
//        }
    }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(15.dp)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        if (currentState is UserListScreenState.Users) {
            if(currentState.users.isNotEmpty()) {
                items(
                    currentState.users.size
                )
                {
                    val user = currentState.users[it]
                    Text(text = user.name)
                }
            }
            else
            {
                viewModel.requestUsers()
            }

        } else {
            item{
                Text(
                    text = "Empty list",
                    fontSize = 30.sp,
                    color = Color.White
                )
            }
        }

    }
}
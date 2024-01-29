package com.example.shifttestapp.presentation.userList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.shifttestapp.di.viewModelFactory.ViewModelFactory
import com.example.shifttestapp.domain.model.User

@Composable
fun UserListScreen(
    factory: ViewModelFactory,
    onItemClick: (userId: User) -> Unit
) {
    val context = LocalContext.current
    val viewModel: UserListViewModel = viewModel(factory = factory)
    val screenState = viewModel.screenState.collectAsState(UserListScreenState.Initial)
    LaunchedEffect(key1 = true)
    {
        viewModel.loadUsers(context)
    }
    when (val currentState = screenState.value) {
        UserListScreenState.Initial -> {}
        UserListScreenState.Loading -> {
//            CircularProgressIndicator(
//                color = MaterialTheme.colorScheme.background
//            )
        }

        is UserListScreenState.Users -> {

            Scaffold(
                floatingActionButton = {
                    Button(onClick = { viewModel.fetchUsers(context) }) {
                        Text(text = "Reset users")
                    }
                }
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(it)
                        .navigationBarsPadding(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(currentState.users.size)
                    {
                        UserItem(user = currentState.users[it], onItemClick)
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User, onItemClick: (userId: User) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(user) },
        shape = RoundedCornerShape(20.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(72.dp)
            ) {
                SubcomposeAsyncImage(
                    model = user.picture,
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(text = user.name, color = MaterialTheme.colorScheme.secondary)
                Text(text = user.mail, color = MaterialTheme.colorScheme.secondary)
                Text(user.number, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

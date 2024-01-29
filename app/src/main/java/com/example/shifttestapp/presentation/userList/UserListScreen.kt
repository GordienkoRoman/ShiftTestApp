package com.example.shifttestapp.presentation.userList

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.shifttestapp.di.viewModelFactory.ViewModelFactory
import com.example.shifttestapp.domain.model.User
import stud.gilmon.base.utils.launchAndCollectIn

@Composable
fun UserListScreen(factory: ViewModelFactory, onItemClick: (userId: User) -> Unit) {
    val viewModel: UserListViewModel = viewModel(factory = factory)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val screenState = viewModel.screenState.collectAsState(UserListScreenState.Initial)
    val currentState = screenState.value
    val users = rememberSaveable { mutableStateOf(listOf<User>()) }
    if (currentState is UserListScreenState.Users) {
        if (currentState.users.isNotEmpty()) {

        }
    }
    LaunchedEffect(key1 = true) {
        if (users.value.isEmpty())
            viewModel.fetchUsers()
    }
    SideEffect {
        viewModel.remoteRandomUsersStateFlow.launchAndCollectIn(lifecycleOwner.value) {
            if (it != null)
                users.value = it
        }
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
            if (users.value.isNotEmpty()) {
                items(
                    users.value.size
                )
                {

                    UserItem(user = users.value[it], onItemClick)
                }
            } else {
                users.value
            }

        } else {
            item {
                Text(
                    text = "Empty list",
                    fontSize = 30.sp,
                    color = Color.White
                )
            }
        }

    }
}

@Composable
fun UserItem(user: User, onItemClick: (userId: User) -> Unit) {
    val context = LocalContext.current

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
                Text(text = user.name)
                Button(onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:") // Only email apps handle this.
                        putExtra(Intent.EXTRA_SUBJECT, "Hi ${user.name},")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(user.mail))
                    }
                    startActivity(context, intent, null)
                }) {
                    Text(text = user.mail, color = Color.Yellow)
                }
                Button(
                    onClick = {
                        val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${user.number}"))
                        ContextCompat.startActivity(context, call, null)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        user.number,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

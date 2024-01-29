package com.example.shifttestapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shifttestapp.BaseApplication
import com.example.shifttestapp.Screen
import com.example.shifttestapp.di.viewModelFactory.ViewModelFactory
import com.example.shifttestapp.domain.model.User
import com.example.shifttestapp.presentation.theme.ShiftTestAppTheme
import com.example.shifttestapp.presentation.userInfo.UserInfoScreen
import com.example.shifttestapp.presentation.userList.UserListScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as BaseApplication).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            ShiftTestAppTheme {
                NavHost(
                    modifier = Modifier,
                    navController = navController,
                    startDestination = Screen.UserList.route,
                    route = Screen.ROUTE_MAIN_GRAPH
                )
                {
                    composable(route = Screen.UserList.route)
                    {
                        UserListScreen(viewModelFactory,
                            onItemClick = {
                                navController.navigate(Screen.getRouteWithArgs(it))
                            }
                        )
                    }
                    composable(route = Screen.UserInfo.route + "/{${Screen.KEY_USER}}",
                        arguments = listOf(
                            navArgument(Screen.KEY_USER) {
                                type = User.NavigationType
                            }),
                        enterTransition = {
                            slideIntoContainer(
                                animationSpec = tween(300, easing = EaseIn),
                                towards = AnimatedContentTransitionScope.SlideDirection.Left
                            )
                        })
                    {
                        val user = it.arguments?.getParcelable(Screen.KEY_USER) ?: User()
                        if (user.id == "") {
                            Toast.makeText(baseContext, "navigation error", Toast.LENGTH_SHORT)
                                .show()
                        } else
                            UserInfoScreen(user)
                    }
                }
            }
        }
    }
}

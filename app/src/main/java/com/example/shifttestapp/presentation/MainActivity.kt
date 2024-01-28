package com.example.shifttestapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.shifttestapp.BaseApplication
import com.example.shifttestapp.di.viewModelFactory.ViewModelFactory
import com.example.shifttestapp.presentation.theme.ShiftTestAppTheme
import com.example.shifttestapp.presentation.userList.UserListScreem
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
            ShiftTestAppTheme {
                Box(modifier = Modifier.fillMaxSize()
                    .background(Color.Red)){

                }
               UserListScreem(factory =viewModelFactory )
            }
        }
    }
}

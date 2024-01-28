package com.example.shifttestapp

import android.app.Application
import com.example.shifttestapp.di.components.AppComponent
import com.example.shifttestapp.di.components.DaggerAppComponent

class BaseApplication  : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

}
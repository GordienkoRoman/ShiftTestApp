package com.example.shifttestapp.di.components

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.shifttestapp.di.modules.AppModule
import com.example.shifttestapp.di.modules.DataBaseModule
import com.example.shifttestapp.di.modules.ViewModelModule
import com.example.shifttestapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
annotation class AppScope

@AppScope
@Component(
    modules = [AppModule::class,
        ViewModelModule::class,
        DataBaseModule::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}
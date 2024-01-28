package com.example.shifttestapp.di.modules

import com.example.shifttestapp.data.remote.RandomUserApi
import com.example.shifttestapp.di.components.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {


    @AppScope
    @Provides
    fun provideRandomUserApiService(): RandomUserApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RandomUserApi::class.java)
    }
}
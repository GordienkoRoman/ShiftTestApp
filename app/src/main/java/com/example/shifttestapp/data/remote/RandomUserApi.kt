package com.example.shifttestapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("?")
    suspend fun getUsers(
        @Query("results") results: Int = 50 //Можно было дать возможность
    ):  Results                            // выбирать пользователю, но в ТЗ не было
}
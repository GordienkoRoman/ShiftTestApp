package com.example.shifttestapp.data.remote

import com.example.shifttestapp.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("?")
    suspend fun getUsers(
        @Query("results") results: Int = 5
    ):  Results
}
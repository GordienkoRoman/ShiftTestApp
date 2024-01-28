package com.example.shifttestapp.data.remote

data class Results(
    val results: List<Result>
)
data class Result(
    val email: String,
    val name: Name,
    val picture: Picture,
    val phone: String
)

data class Name(
    val first: String,
    val last: String,
)
data class Picture(
    val medium:String
)
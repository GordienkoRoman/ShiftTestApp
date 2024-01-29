package com.example.shifttestapp.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Results(
    val results: List<Result>
)
data class Result(
    val login: Login,
    val gender: String,
    val location: Location,
    val email: String,
    val name: Name,
    val picture: Picture,
    val phone: String,
    val dob:Dob
)
data class Login(
    val uuid:String,
    val username:String,
)
data class Name(
    val title:String,
    val first: String,
    val last: String,
)
data class Location(
    val city:String,
    val state:String,
    val country:String,
    val coordinates:Coordinates,
)
@Parcelize
data class Coordinates(
    val latitude:String,
    val longitude:String
):Parcelable
data class Picture(
    val medium:String,
    val large:String
)
data class Dob(
    val age:Int
)
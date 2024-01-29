package com.example.shifttestapp.domain.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.example.shifttestapp.data.remote.Coordinates
import com.example.shifttestapp.data.remote.Result
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val name: String = "",
    val picture: String = "",
    val mail: String = "",
    val number: String = "",
    val gender: String = "",
    val city: String = "",
    val state: String = "",
    val country: String = "",
    val coordinates: Coordinates = Coordinates("", ""),
    val age: Int = -1
):Parcelable {
    companion object {
        fun mapToUser(result: Result): User {

            return User(
                id = result.login.uuid,
                name = "${result.name.first} ${result.name.last}",
                picture = result.picture.medium,
                mail = result.email,
                number = result.phone,
                gender = result.gender,
                city = result.location.city,
                state = result.location.state,
                country = result.location.country,
                coordinates = Coordinates(result.location.coordinates.latitude,
                    result.location.coordinates.longitude),
                age = result.dob.age
            )
        }
        val NavigationType: NavType<User> = object : NavType<User>(false) {

            override fun get(bundle: Bundle, key: String): User? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): User {
                return Gson().fromJson(value, User::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: User) {
                bundle.putParcelable(key, value)
            }
        }
    }

}

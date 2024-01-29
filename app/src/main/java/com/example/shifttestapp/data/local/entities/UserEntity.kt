package com.example.shifttestapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shifttestapp.data.remote.Coordinates
import com.example.shifttestapp.domain.model.User

@Entity(
    tableName = "users"
)
data class UserEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "picture") val picture:String,
    @ColumnInfo(name = "mail") val mail: String,
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "gender")val gender: String,
    @ColumnInfo(name = "city")val city: String,
    @ColumnInfo(name = "state")val state: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "latitude") val latitude:String,
    @ColumnInfo(name = "longitude") val longitude:String,
    @ColumnInfo(name = "age")val age: Int,
    @PrimaryKey
    val userId: String,
) {

    /*  companion object {
          const val TABLE_NAME = "accounts"
      }*/
    fun toUser(): User = User(
        id = userId,
        name = name,
        picture = picture,
        mail = mail,
        number=number,
        gender = gender,
        city = city,
        state = state,
        country = country,
        age = age,
        coordinates = Coordinates(latitude,longitude)
    )
}
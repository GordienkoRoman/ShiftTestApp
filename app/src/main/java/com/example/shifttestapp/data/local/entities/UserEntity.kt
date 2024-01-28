package com.example.shifttestapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shifttestapp.domain.model.User

@Entity(
    tableName = "users"
)
data class UserEntity(
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "avatar_url") val avatarUrl:String = "",
    @ColumnInfo(name = "mail") val mail: String = "",
    @ColumnInfo(name = "number") val number: String = "",
    @PrimaryKey
    val userId: Long,
) {

    /*  companion object {
          const val TABLE_NAME = "accounts"
      }*/
    fun toUser(): User = User(
        id = userId,
        name = name,
        img = avatarUrl,
        mail = mail,
        number=number
    )
}
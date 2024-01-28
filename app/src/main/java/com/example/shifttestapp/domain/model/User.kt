package com.example.shifttestapp.domain.model

import com.example.shifttestapp.data.remote.Result

data class User(
    val id:Long=0,
    val name:String="",
    val img : String="",
    val mail : String="",
    val number: String=""
)
{
    companion object
    {
        fun mapToUser(result:Result):User{
            return User(0,result.name.last+" "+result.name.last,result.picture.medium,result.email,result.phone)
        }
    }

}

package com.example.shifttestapp

import android.net.Uri
import com.example.shifttestapp.domain.model.User
import com.google.gson.Gson
import okio.ByteString.Companion.encode


sealed class Screen(val route: String) {
    data object UserList : Screen(route = ROUTE_USER_LIST)
    data object UserInfo : Screen(route = ROUTE_USER_INFO)

    companion object {
        const val KEY_USER = "auth_user"
        const val ROUTE_USER_INFO = "user_info_screen"
        const val ROUTE_MAIN_GRAPH = "main_graph"
        const val ROUTE_USER_LIST = "user_list_screen"
        fun getRouteWithArgs(user: User): String {
            val userJson = Gson().toJson(user)
            return "$ROUTE_USER_INFO/${Uri.encode(userJson)}"
        }
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }


    fun getRouteWithArgs(user: User): String {
        val userJson = Gson().toJson(User)
        return "$ROUTE_USER_INFO/${userJson.encode()}"
    }
}

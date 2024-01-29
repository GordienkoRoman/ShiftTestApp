package com.example.shifttestapp.data.mapper

import com.example.shifttestapp.data.local.entities.UserEntity
import com.example.shifttestapp.domain.model.User


fun User.toEntity():UserEntity = UserEntity(name = name,
    picture = picture,
    mail = mail,
    number = number,
    userId = id,
    age = age,
    latitude = coordinates.latitude,
    longitude = coordinates.longitude,
    city = city,
    state = state,
    country = country,
    gender = gender)
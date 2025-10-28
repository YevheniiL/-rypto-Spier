package com.home.petprojectv2.data.dto

import com.home.petprojectv2.domain.model.User

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
)

fun UserDto.toUser(): User =
    User(
        id = id,
        name = name,
        email = email,
    )

package com.home.petprojectv2.data.api

import com.home.petprojectv2.data.dto.UserDto
import retrofit2.http.POST

interface UserAuthApiService {
    @POST("signInWithPassword")
    suspend fun userSignIn(): UserDto
}

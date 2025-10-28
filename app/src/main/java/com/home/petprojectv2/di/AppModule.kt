package com.home.petprojectv2.di

import com.home.petprojectv2.BuildConfig
import com.home.petprojectv2.data.api.UserAuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): UserAuthApiService =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.USER_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAuthApiService::class.java)
}

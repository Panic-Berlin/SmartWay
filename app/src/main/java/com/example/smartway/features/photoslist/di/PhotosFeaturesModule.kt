package com.example.smartway.features.photoslist.di

import com.example.smartway.features.photoslist.data.api.PhotosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class PhotosFeaturesModule {

    @Provides
    fun providePhotosApi(retrofit: Retrofit) = retrofit.create(
        PhotosApi::class.java
    )
}
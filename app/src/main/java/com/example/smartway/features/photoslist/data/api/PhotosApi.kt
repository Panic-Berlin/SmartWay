package com.example.smartway.features.photoslist.data.api

import com.example.smartway.features.photoslist.data.model.PhotoResponseItem
import com.example.smartway.utils.KeyUtils.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosApi {

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : List<PhotoResponseItem>
}
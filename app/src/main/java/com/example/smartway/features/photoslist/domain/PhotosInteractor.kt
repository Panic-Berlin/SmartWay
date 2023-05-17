package com.example.smartway.features.photoslist.domain

import com.example.smartway.features.photoslist.data.PhotosRepo
import com.example.smartway.utils.safeRequest
import javax.inject.Inject

class PhotosInteractor @Inject constructor(
    private val photosRepo: PhotosRepo
){

    suspend fun getPhotos(page: Int, perPage: Int) = safeRequest {
        photosRepo.getPhotos(page, perPage)
    }
}
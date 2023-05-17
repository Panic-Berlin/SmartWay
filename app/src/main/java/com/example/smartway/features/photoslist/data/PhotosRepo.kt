package com.example.smartway.features.photoslist.data

import com.example.smartway.features.photoslist.data.api.PhotosApi
import com.example.smartway.features.photoslist.data.model.mapper.PhotoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosRepo @Inject constructor(
    private val api: PhotosApi,
    private val photoMapper: PhotoMapper
) {

    suspend fun getPhotos(page: Int, perPage: Int) = withContext(Dispatchers.IO){
        api.getPhotos(page, perPage).map {
                res -> photoMapper.map(res)
        }
    }
}
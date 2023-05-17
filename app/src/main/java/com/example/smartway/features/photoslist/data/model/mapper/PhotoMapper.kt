package com.example.smartway.features.photoslist.data.model.mapper

import com.example.smartway.features.photoslist.data.model.PhotoResponseItem
import com.example.smartway.features.photoslist.domain.model.PhotoItem
import javax.inject.Inject

class PhotoMapper @Inject constructor(
    private val urlsMapper: UrlsMapper
) {

    fun map(photoResponseItem: PhotoResponseItem) = PhotoItem(
        urls = photoResponseItem.urls.let {
            urlsMapper.map(it)
        }
    )
}
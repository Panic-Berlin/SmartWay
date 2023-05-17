package com.example.smartway.features.photoslist.data.model.mapper

import com.example.smartway.features.photoslist.data.model.UrlsResponse
import com.example.smartway.features.photoslist.domain.model.Urls
import javax.inject.Inject

class UrlsMapper @Inject constructor(){

    fun map(urlsResponse: UrlsResponse) = Urls(
        regular = urlsResponse.regular
    )
}
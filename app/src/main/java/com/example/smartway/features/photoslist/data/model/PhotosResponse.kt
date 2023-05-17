package com.example.smartway.features.photoslist.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponseItem(
    val urls: UrlsResponse
)

@JsonClass(generateAdapter = true)
data class UrlsResponse(
    val regular: String
)
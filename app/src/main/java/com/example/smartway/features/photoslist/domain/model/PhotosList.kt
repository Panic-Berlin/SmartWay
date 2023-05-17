package com.example.smartway.features.photoslist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoItem(
    val urls: Urls
) : Parcelable

@Parcelize
data class Urls(
    val regular: String
) : Parcelable
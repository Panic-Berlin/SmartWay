package com.example.smartway.features.photo.presentation

import androidx.lifecycle.ViewModel
import com.example.smartway.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor() : ViewModel() {

    val getPhoto = SingleLiveEvent<String>()

    fun getPhotoUrl(photoUrl: String) {
        getPhoto.call(photoUrl)
    }
}
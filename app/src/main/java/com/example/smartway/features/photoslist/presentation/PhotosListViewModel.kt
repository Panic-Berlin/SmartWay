package com.example.smartway.features.photoslist.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartway.features.photoslist.domain.PhotosInteractor
import com.example.smartway.features.photoslist.domain.model.PhotoItem
import com.example.smartway.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val photosInteractor: PhotosInteractor
) : ViewModel(){

    private val _photos = MutableLiveData<ViewState<List<PhotoItem>>>()
    val photos get() = _photos.asLiveData()

    val goToPhoto = SingleLiveEvent<PhotoItem>()



    fun getPhotos(page: Int, perPage: Int){
        viewModelScope.launch {
            _photos.value = photosInteractor.getPhotos(page, perPage).asViewState()
            Log.d("PhotosViewModel", "getPhotos: ${_photos.value}")
        }
    }

    fun onPhotoClick(photo: PhotoItem){
        goToPhoto.call(photo)
    }
}
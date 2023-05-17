package com.example.smartway.features.photoslist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.smartway.R
import com.example.smartway.databinding.ItemPhotoBinding
import com.example.smartway.features.photoslist.domain.model.PhotoItem

class PhotosAdapter(
    private val photos: List<PhotoItem>,
    private val onPhotoClick: (PhotoItem) -> Unit
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    class PhotosViewHolder(view: View, private val onPhotoClick: (PhotoItem) -> Unit) :
        ViewHolder(view) {

        private val viewBinding: ItemPhotoBinding by viewBinding(ItemPhotoBinding::bind)
        fun bind(photo: PhotoItem) {
            Glide.with(viewBinding.ivPhoto).load(photo.urls.regular).into(viewBinding.ivPhoto)
            viewBinding.pbProgress.isVisible = false
            viewBinding.ivPhoto.setOnClickListener {
                onPhotoClick.invoke(photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val cellForPhoto =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(cellForPhoto, onPhotoClick)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}
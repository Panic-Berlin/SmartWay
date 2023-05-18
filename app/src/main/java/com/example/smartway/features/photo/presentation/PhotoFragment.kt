package com.example.smartway.features.photo.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.smartway.R
import com.example.smartway.databinding.FragmentPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private val viewBinding: FragmentPhotoBinding by viewBinding(FragmentPhotoBinding::bind)
    private val viewModel: PhotoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun observe() {
        viewModel.getPhoto.observe(viewLifecycleOwner) {
            Glide.with(viewBinding.myZoomageView).load(it).into(viewBinding.myZoomageView)
        }
    }

    private fun initViews() {
        (arguments?.getString("photo") as String).let {
            viewModel.getPhotoUrl(it)
        }

    }
}
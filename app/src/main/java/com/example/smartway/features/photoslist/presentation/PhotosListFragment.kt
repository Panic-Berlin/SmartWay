package com.example.smartway.features.photoslist.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.smartway.R
import com.example.smartway.databinding.FragmentPhotosListBinding
import com.example.smartway.features.photoslist.domain.model.PhotoItem
import com.example.smartway.features.photoslist.presentation.adapter.PhotosAdapter
import com.example.smartway.utils.KeyUtils.Companion.PAGE_KEY
import com.example.smartway.utils.KeyUtils.Companion.PHOTO_KEY
import com.example.smartway.utils.KeyUtils.Companion.POSITION_KEY
import com.example.smartway.utils.ViewState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosListFragment : Fragment(R.layout.fragment_photos_list) {

    private val viewBinding: FragmentPhotosListBinding by viewBinding(FragmentPhotosListBinding::bind)
    private val viewModel: PhotosListViewModel by viewModels()
    private var page = 1
    private var pageSize = 30

    private var photoItems: MutableList<PhotoItem> = mutableListOf()
    private var manager: GridLayoutManager? = null

    private var isLoading = false
    private var isLastPage = false
    private var position = -1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null){
            photoItems = savedInstanceState.getParcelableArrayList(PHOTO_KEY)!!
            position = savedInstanceState.getInt(POSITION_KEY)
            page = savedInstanceState.getInt(PAGE_KEY)
        }
        initViews()
        observe()
    }

    private fun initViews() {
        viewModel.getPhotos(page, pageSize)
        manager = GridLayoutManager(requireContext(), 3)
        viewBinding.rvPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                position = manager?.findFirstCompletelyVisibleItemPosition()!!
                val visibleItem = manager?.childCount
                val totalItem = manager?.itemCount
                val firstVisibleItemPosition = manager?.findFirstVisibleItemPosition()
                if (!isLoading && !isLastPage){
                    if ((visibleItem!! + firstVisibleItemPosition!! >= totalItem!!)
                        && firstVisibleItemPosition >= 0 &&
                            totalItem >= pageSize){
                        page++
                        isLoading = true
                        viewModel.getPhotos(page, pageSize)
                    }
                }
            }

        })
    }

    private fun observe() {
        viewBinding.rvPhotos.layoutManager = manager
        viewModel.photos.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Show -> {
                    viewBinding.pbProgress.isVisible = false
                    photoItems += it.data
                    viewBinding.rvPhotos.adapter = PhotosAdapter(photoItems){ photo ->
                        viewModel.onPhotoClick(photo)
                    }
                    isLoading = false
                    isLastPage = if (it.data.isNotEmpty()){
                        it.data.size < pageSize
                    }else true
                    manager?.scrollToPosition(position)
                }
                is ViewState.Error -> {
                    isLoading = false
                    view?.let { _view -> Snackbar.make(_view, R.string.error, Snackbar.LENGTH_LONG).show() }
                }
                else -> {
                    isLoading = false
                    view?.let { _view -> Snackbar.make(_view, R.string.something_went_wrong, Snackbar.LENGTH_LONG).show() }
                }
            }
        }
        viewModel.goToPhoto.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_photosListFragment_to_photoFragment, bundleOf(
                "photo" to it.urls.regular
            ))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(PHOTO_KEY, ArrayList(photoItems))
        outState.putInt(POSITION_KEY, position)
        outState.putInt(PAGE_KEY, page)
    }
}
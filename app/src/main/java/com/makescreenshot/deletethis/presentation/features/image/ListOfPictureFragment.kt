package com.makescreenshot.deletethis.presentation.features.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.makescreenshot.deletethis.data.network.response.PhotosListData
import com.makescreenshot.deletethis.databinding.FragmentListOfPictureBinding
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.features.DashboardViewModel
import com.makescreenshot.deletethis.presentation.features.image.recyclerview.adapter.ImagesAdapter
import com.makescreenshot.deletethis.presentation.utils.Inflate
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException


@AndroidEntryPoint
class ListOfPictureFragment : BaseVMFragment<FragmentListOfPictureBinding, DashboardViewModel>() {

    private lateinit var imageAdapter: ImagesAdapter

    override val inflate: Inflate<FragmentListOfPictureBinding>
        get() = FragmentListOfPictureBinding::inflate

    override val viewModel: DashboardViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun FragmentListOfPictureBinding.initUI() {
        setupImageList()
        with(binding) {
            recycler.adapter = imageAdapter
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupImageList() {
        imageAdapter = ImagesAdapter(
            onImagePickClick = ::pickImage
        )
    }

    private fun pickImage(data: PhotosListData) {
        try {
            Glide.with(this)//=)
                .asBitmap()
                .load(data.urls?.regular)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        bitmap: Bitmap,
                        transition: Transition<in Bitmap>?,
                    ) {
                        viewModel.setNewPhoto(bitmap)
                        requireActivity().onBackPressed()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun DashboardViewModel.observeViewModel() {
        photosListData.observe(viewLifecycleOwner) { photoList ->
            if (photoList.isNotEmpty()) {
                imageAdapter.updateImagesWithScroll(photoList) {}
            }
        }
    }
}
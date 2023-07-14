package com.makescreenshot.deletethis.presentation.features.image

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.makescreenshot.deletethis.R
import com.makescreenshot.deletethis.databinding.FragmentPicturePreviewBinding
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.features.DashboardViewModel
import com.makescreenshot.deletethis.presentation.utils.Inflate
import com.makescreenshot.deletethis.presentation.utils.findNavController
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@AndroidEntryPoint
class PicturePreviewFragment : BaseVMFragment<FragmentPicturePreviewBinding, DashboardViewModel>() {

    override val inflate: Inflate<FragmentPicturePreviewBinding>
        get() = FragmentPicturePreviewBinding::inflate

    override val viewModel: DashboardViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun FragmentPicturePreviewBinding.initUI() {
        viewModel.loadImages()
        openList.setOnClickListener {
            findNavController().navigate(R.id.listOfPictures)
        }
        setFilter.setOnClickListener {
            viewModel.setFilter()
        }

        saveToGallery.setOnClickListener {
            saveImageToGallery(binding.newImage.drawToBitmap())
        }
    }

    override fun DashboardViewModel.observeViewModel() {
        photoTaken.observe(viewLifecycleOwner) {
            it?.let { it1 ->
                loadWithPlaceHolder(
                    it1,
                    binding.newImage,
                    R.drawable.ic_launcher_foreground
                )
            }
        }
    }

    private fun saveImageToGallery(bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requireContext().contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(
                requireContext(), getString(R.string.saved), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun loadWithPlaceHolder(
        item: Bitmap,
        into: ImageView,
        placeholder: Int,
    ) {
        Glide.with(into.context)
            .load(item)
            .placeholder(placeholder)
            .into(into)
    }
}
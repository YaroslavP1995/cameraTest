package com.makescreenshot.deletethis.presentation.features.image.recyclerview.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makescreenshot.deletethis.R
import com.makescreenshot.deletethis.databinding.ItemImagesBinding
import com.makescreenshot.deletethis.data.response.PhotosListData

class ShowImagesViewHolder(
    private val binding: ItemImagesBinding,
    private val onImagePickClick: (PhotosListData) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhotosListData) {
        loadImage(item)
        initDeleteButton(item)
    }

    private fun initDeleteButton(item: PhotosListData) {
        binding.root.apply {
            setOnClickListener {
                onImagePickClick.invoke(item)
            }
        }
    }

    private fun loadImage(item: PhotosListData) {
        loadWithPlaceHolder(item, binding.image, R.drawable.ic_launcher_foreground)
    }

    fun loadWithPlaceHolder(
        item: PhotosListData,
        into: ImageView,
        placeholder: Int,
    ) {
        Glide.with(into.context)
            .load(item.path)
            .placeholder(placeholder)
            .into(into)
    }
}
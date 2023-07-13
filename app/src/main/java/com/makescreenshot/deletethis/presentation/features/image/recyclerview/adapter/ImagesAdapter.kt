package com.makescreenshot.deletethis.presentation.features.image.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makescreenshot.deletethis.databinding.ItemImagesBinding
import com.makescreenshot.deletethis.presentation.features.image.recyclerview.viewholder.ShowImagesViewHolder
import com.makescreenshot.deletethis.presentation.utils.comparator.IntIdComparator
import com.makescreenshot.deletethis.data.response.PhotosListData

class ImagesAdapter(
    private val onImagePickClick: (PhotosListData) -> Unit,
) : ListAdapter<PhotosListData, RecyclerView.ViewHolder>(IntIdComparator<PhotosListData>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ShowImagesViewHolder(getImageListBinding(inflater, parent), onImagePickClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowImagesViewHolder -> holder.bind(getItem(position))
        }
    }

    private fun updateImages(newImages: List<PhotosListData>) {
        submitList(newImages)
    }

    fun updateImagesWithScroll(images: List<PhotosListData>, scrollTo: (Int) -> Unit) {
        updateImages(images)
        scrollTo(0)
    }

    private fun getImageListBinding(
        inflater: LayoutInflater, group: ViewGroup,
    ): ItemImagesBinding {
        return ItemImagesBinding.inflate(inflater, group, false)
    }

}
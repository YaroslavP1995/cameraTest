package com.makescreenshot.deletethis.presentation.features.image

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.makescreenshot.deletethis.R
import com.makescreenshot.deletethis.databinding.FragmentPicturePreviewBinding
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.features.DashboardViewModel
import com.makescreenshot.deletethis.presentation.utils.Inflate
import dagger.hilt.android.AndroidEntryPoint

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
    }
}
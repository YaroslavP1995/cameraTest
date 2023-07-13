package com.makescreenshot.deletethis.presentation.work_with_image

import androidx.fragment.app.viewModels
import com.makescreenshot.deletethis.databinding.FragmentPicturePreviewBinding
import com.makescreenshot.deletethis.presentation.DashboardViewModel
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.utils.Inflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PicturePreviewFragment : BaseVMFragment<FragmentPicturePreviewBinding, DashboardViewModel>() {

    override val inflate: Inflate<FragmentPicturePreviewBinding>
        get() = FragmentPicturePreviewBinding::inflate

    override val viewModel: DashboardViewModel by viewModels()

    override fun FragmentPicturePreviewBinding.initUI() {
        with(binding) {
        }
    }

    override fun DashboardViewModel.observeViewModel() {}
}
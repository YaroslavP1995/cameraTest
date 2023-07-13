package com.makescreenshot.deletethis.presentation.features.image

import androidx.fragment.app.viewModels
import com.makescreenshot.deletethis.databinding.FragmentListOfPictureBinding
import com.makescreenshot.deletethis.databinding.FragmentPicturePreviewBinding
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.features.DashboardViewModel
import com.makescreenshot.deletethis.presentation.utils.Inflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfPictureFragment : BaseVMFragment<FragmentListOfPictureBinding, DashboardViewModel>() {

    override val inflate: Inflate<FragmentListOfPictureBinding>
        get() = FragmentListOfPictureBinding::inflate

    override val viewModel: DashboardViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun FragmentListOfPictureBinding.initUI() {
        with(binding) {
        }
    }

    override fun DashboardViewModel.observeViewModel() {}

}
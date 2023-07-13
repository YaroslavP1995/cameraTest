package com.makescreenshot.deletethis.presentation.features.camera

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.makescreenshot.deletethis.R
import com.makescreenshot.deletethis.databinding.FragmentCameraPreviewBinding
import com.makescreenshot.deletethis.presentation.features.DashboardViewModel
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.utils.Inflate
import com.makescreenshot.deletethis.presentation.utils.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraPreviewFragment : BaseVMFragment<FragmentCameraPreviewBinding, DashboardViewModel>() {


    override val inflate: Inflate<FragmentCameraPreviewBinding>
        get() = FragmentCameraPreviewBinding::inflate

    override val viewModel: DashboardViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun FragmentCameraPreviewBinding.initUI() {
        with(binding) {
            goToPictureScreen.setOnClickListener {
                findNavController().navigate(R.id.picturePreview)
            }
        }
    }

}
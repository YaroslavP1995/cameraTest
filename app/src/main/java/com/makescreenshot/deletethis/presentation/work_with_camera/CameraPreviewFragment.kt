package com.makescreenshot.deletethis.presentation.work_with_camera

import androidx.fragment.app.viewModels
import com.makescreenshot.deletethis.databinding.FragmentCameraPreviewBinding
import com.makescreenshot.deletethis.presentation.DashboardViewModel
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.utils.Inflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraPreviewFragment : BaseVMFragment<FragmentCameraPreviewBinding, DashboardViewModel>() {


    override val inflate: Inflate<FragmentCameraPreviewBinding>
        get() = FragmentCameraPreviewBinding::inflate

    override val viewModel: DashboardViewModel by viewModels()

    override fun FragmentCameraPreviewBinding.initUI() {
        with(binding) {

        }
    }

}
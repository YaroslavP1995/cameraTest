package com.makescreenshot.deletethis.presentation.features.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.makescreenshot.deletethis.R
import com.makescreenshot.deletethis.databinding.FragmentCameraPreviewBinding
import com.makescreenshot.deletethis.presentation.base.BaseVMFragment
import com.makescreenshot.deletethis.presentation.features.DashboardViewModel
import com.makescreenshot.deletethis.presentation.utils.Inflate
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class CameraPreviewFragment : BaseVMFragment<FragmentCameraPreviewBinding, DashboardViewModel>() {

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            var permissionGranted = true
            permissions.entries.forEach {
                if (it.key in REQUIRED_PERMISSIONS && !it.value)
                    permissionGranted = false
            }
            if (!permissionGranted) {
                Toast.makeText(
                    requireContext(), "Permission request denied", Toast.LENGTH_SHORT
                ).show()
            } else {
                swapMode(false)
            }
        }

    private lateinit var cameraExecutor: ExecutorService

    override val inflate: Inflate<FragmentCameraPreviewBinding>
        get() = FragmentCameraPreviewBinding::inflate

    override val viewModel: DashboardViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun FragmentCameraPreviewBinding.initUI() {
        cameraExecutor = Executors.newSingleThreadExecutor()
        showCamera()
        initOnClickListeners()
    }

    private fun showCamera() {
        if (allPermissionsGranted()) {
            swapMode(false)
        } else {
            requestPermissions()
        }
    }

    private fun initOnClickListeners() {
        with(binding) {
            imageCaptureButton.setOnClickListener {
                viewModel.takePhoto()
            }
            changeCameraFon.setOnCheckedChangeListener { _, isChecked ->
                swapMode(isChecked)
            }
            goToPictureScreen.setOnClickListener {
                findNavController().navigate(R.id.picturePreview)
            }
        }
    }

    private fun swapMode(isChecked: Boolean) {
        viewModel.startCamera(
            binding.viewFinder,
            cameraExecutor,
            this@CameraPreviewFragment,
            if (isChecked) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
        )
    }

    private fun requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireActivity().applicationContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}
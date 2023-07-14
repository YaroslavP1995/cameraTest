package com.makescreenshot.deletethis.data.repository

import android.graphics.Bitmap
import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.makescreenshot.deletethis.data.model.CameraManager
import com.makescreenshot.deletethis.data.model.ImageProcessor
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class CameraRepository @Inject constructor(
    private val cameraManager: CameraManager,
    private val imageProcessor: ImageProcessor,
) {
    fun startCamera(
        previewView: PreviewView,
        cameraExecutor: ExecutorService,
        lifecycleOwner: LifecycleOwner,
        cameraSelector: CameraSelector,
    ) {
        cameraManager.startCamera(previewView, cameraExecutor, lifecycleOwner, cameraSelector)
    }

    fun takePhoto(onImageCaptured: (Bitmap?) -> Unit) {
        cameraManager.takePhoto {
            onImageCaptured(it)
        }
    }

    fun useBlackAndWhiteFilter(bitmap: Bitmap, onImageColored: (Bitmap?) -> Unit) {
        imageProcessor.applyFilter(bitmap) {
            onImageColored(it)
        }
    }
}
package com.makescreenshot.deletethis.presentation.features

import android.graphics.Bitmap
import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makescreenshot.deletethis.data.network.response.PhotosListData
import com.makescreenshot.deletethis.data.repository.CameraRepository
import com.makescreenshot.deletethis.data.repository.PhotosRepository
import com.makescreenshot.deletethis.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val cameraRepository: CameraRepository,
) : BaseViewModel() {

    private val _photosListData = MutableLiveData<List<PhotosListData?>>()
    val photosListData: LiveData<List<PhotosListData?>>
        get() = _photosListData

    private val _photoTaken = MutableLiveData<Bitmap?>()
    val photoTaken: LiveData<Bitmap?>
        get() = _photoTaken

    fun startCamera(
        previewView: PreviewView,
        cameraExecutor: ExecutorService,
        lifecycleOwner: LifecycleOwner,
        cameraSelector: CameraSelector,
    ) {
        cameraRepository.startCamera(previewView, cameraExecutor, lifecycleOwner, cameraSelector)
    }

    fun takePhoto() {
        viewModelScope.launch {
            cameraRepository.takePhoto {
                setNewPhoto(it)
            }
        }

    }

    fun setFilter() {
        viewModelScope.launch {
            _photoTaken.value?.let {
                cameraRepository.useBlackAndWhiteFilter(it) {
                    setNewPhoto(it)
                }
            }
        }
    }

    fun loadImages() {
        viewModelScope.launch {
            photosRepository.loadImages()
                .onSuccess {
                    _photosListData.value = it
                }
                .onFailure {

                }
        }
    }

    fun setNewPhoto(bitmap: Bitmap?) {
        _photoTaken.value = bitmap
    }
}
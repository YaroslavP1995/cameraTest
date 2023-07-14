package com.makescreenshot.deletethis.presentation.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makescreenshot.deletethis.data.network.response.PhotosListData
import com.makescreenshot.deletethis.data.repository.PhotosRepository
import com.makescreenshot.deletethis.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
) : BaseViewModel() {

    private val _photosListData = MutableLiveData<List<PhotosListData?>>()
    val photosListData: LiveData<List<PhotosListData?>>
        get() = _photosListData

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
}
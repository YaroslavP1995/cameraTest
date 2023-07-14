package com.makescreenshot.deletethis.data.repository

import com.makescreenshot.deletethis.data.network.safeResultCall
import com.makescreenshot.deletethis.data.network.service.PhotoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val service: PhotoApiService,
) {
    suspend fun loadImages() = withContext(Dispatchers.IO) {
        safeResultCall {
            service.loadImages()
        }
    }
}
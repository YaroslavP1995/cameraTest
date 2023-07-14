package com.makescreenshot.deletethis.data.network.service

import com.makescreenshot.deletethis.data.network.response.PhotosListData
import retrofit2.http.GET

interface PhotoApiService {

    @GET("photos/")
    suspend fun loadImages(
    ): List<PhotosListData>
}
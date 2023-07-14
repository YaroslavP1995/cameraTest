package com.makescreenshot.deletethis.domain.di

import android.content.Context
import com.makescreenshot.deletethis.data.model.CameraManager
import com.makescreenshot.deletethis.data.model.ImageProcessor
import com.makescreenshot.deletethis.data.repository.CameraRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CameraModule {
    @Singleton
    @Provides
    fun provideCameraManager(@ApplicationContext context: Context): CameraManager {
        return CameraManager(context)
    }

    @Singleton
    @Provides
    fun provideCameraRepository(
        cameraManager: CameraManager,
        imageProcessor: ImageProcessor,
    ): CameraRepository {
        return CameraRepository(cameraManager, imageProcessor)
    }

    @Singleton
    @Provides
    fun provideImageProcessor(): ImageProcessor {
        return ImageProcessor()
    }
}
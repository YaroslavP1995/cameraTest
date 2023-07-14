package com.makescreenshot.deletethis.domain.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.makescreenshot.deletethis.BuildConfig
import com.makescreenshot.deletethis.data.network.header.HeaderStorage
import com.makescreenshot.deletethis.data.network.header.InterceptorHeader
import com.makescreenshot.deletethis.data.network.header.OkHttpModuleProvider
import com.makescreenshot.deletethis.data.network.header.TokenHeaderStorage
import com.makescreenshot.deletethis.data.network.service.PhotoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

@InstallIn(SingletonComponent::class)
@Module
class NetworkApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): PhotoApiService {
        return retrofit.create(PhotoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenHeaderStorage(): HeaderStorage {
        return TokenHeaderStorage()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        builder: Retrofit.Builder,
        client: OkHttpClient,
    ): Retrofit {
        return builder
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(converterFactory: Converter.Factory): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat(TIME_PATTERN)
            .create()
    }

    @Provides
    @Singleton
    fun provideTokenHeaderInterceptor(headerStorage: HeaderStorage): Interceptor {
        return InterceptorHeader(headerStorage)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpModuleProvider.provideOkHttpClient(headerInterceptor)
    }
}
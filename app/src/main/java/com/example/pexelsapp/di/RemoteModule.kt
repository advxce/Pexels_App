package com.example.pexelsapp.di

import com.example.pexelsapp.BuildConfig
import com.example.pexelsapp.data.network.PexelsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    const val BASE_URL = "https://api.pexels.com/v1/"

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request()
        val requestWithAuth = request.newBuilder()
            .header("Authorization", BuildConfig.API_KEY)
            .build()
        chain.proceed(requestWithAuth)

    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun provideRemoteService(): PexelsApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PexelsApiService::class.java)
}
package com.example.pexelsapp.data

import com.example.pexelsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.pexels.com/v1/"

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

    val api: PexelsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PexelsApiService::class.java)
    }

}
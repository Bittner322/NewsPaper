package com.example.newspaper.di

import com.example.newspaper.data.network.NewsService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

@Module
class NetworkModule {

    private val contentType = "application/json".toMediaType()

    @Provides
    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create()
    }

}
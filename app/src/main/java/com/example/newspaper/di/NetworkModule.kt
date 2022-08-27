package com.example.newspaper.di

import com.example.newspaper.data.network.NewsService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

class NetworkModule {

    private val contentType = "application/json".toMediaType()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    val newsService = retrofit.create<NewsService>()

}
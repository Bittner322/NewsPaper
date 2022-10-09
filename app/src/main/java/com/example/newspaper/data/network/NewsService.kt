package com.example.newspaper.data.network

import com.example.newspaper.BuildConfig
import com.example.newspaper.data.network.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "",
        @Query("from") from: String = "",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy : String = "popularity",
        @Query("apiKey") apiKey : String = BuildConfig.API_KEY,
    ) : NewsResponse

    @GET("top-headlines")
    suspend fun getNewsByCategories(
        @Query("country") country : String = "us",
        @Query("category") category: List<String> = emptyList(),
        @Query("apiKey") apiKey : String = BuildConfig.API_KEY,
    ) : NewsResponse
}
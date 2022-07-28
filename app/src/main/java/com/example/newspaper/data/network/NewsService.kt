package com.example.newspaper.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService{

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "tesla",
        @Query("from") from : String = "",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy : String = "publishedAt",
        @Query("apiKey") apiKey : String = "a5d65121640a45d99c48715b67299a36"
    ) : NewsResponse

}
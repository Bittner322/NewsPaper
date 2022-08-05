package com.example.newspaper.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class NewsResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("articles")
    val articles: List<Articles>
) {
    @Serializable
    data class Articles(
        @SerialName("source")
        val source: Source,
        @SerialName("author")
        val author: String?,
        @SerialName("title")
        val title: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("url")
        val url: String,
        @SerialName("urlToImage")
        val urlToImage: String?,
        @SerialName("publishedAt")
        val publishedAt: String,
        @SerialName("content")
        val content : String
    ) {
        @Serializable
        data class Source (
            @SerialName("id")
            val id: String?,
            @SerialName("name")
            val name: String
        )
    }
}
package com.example.newspaper.data.database

import androidx.room.TypeConverter
import com.example.newspaper.data.network.NewsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ArticleConverter {
    @TypeConverter
    fun fromSource(source: NewsResponse.Articles.Source): String? {
        if (source == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<NewsResponse.Articles.Source?>() {}.type
        return gson.toJson(source, type)
    }

    @TypeConverter
    fun toSource(source: String?): NewsResponse.Articles.Source? {
        if (source == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<NewsResponse.Articles.Source?>() {}.type
        return gson.fromJson<NewsResponse.Articles.Source>(source, type)
    }
}
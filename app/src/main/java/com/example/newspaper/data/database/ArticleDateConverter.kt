package com.example.newspaper.data.database

import androidx.room.TypeConverter
import java.util.*

class ArticleDateConverter {
    @TypeConverter
    fun fromDate(date: Date?): String {
        return date.toString()
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: String?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
}
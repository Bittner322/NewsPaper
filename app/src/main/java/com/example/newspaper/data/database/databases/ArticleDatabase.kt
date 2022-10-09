package com.example.newspaper.data.database.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newspaper.data.database.dao.ArticleDao
import com.example.newspaper.data.database.dao.ArticleHistoryDao
import com.example.newspaper.data.database.dao.CategoryDao
import com.example.newspaper.data.database.dao.SearchedNewsDao
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.data.database.models.ArticleHistory
import com.example.newspaper.data.database.models.Category
import com.example.newspaper.data.database.models.SearchedNewsArticle

@Database(entities = [Article::class, ArticleHistory::class, Category::class, SearchedNewsArticle::class], version = 12, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun historyDao(): ArticleHistoryDao
    abstract fun categoryDao(): CategoryDao
    abstract fun searchedNewsDao(): SearchedNewsDao

    companion object {

        lateinit var INSTANCE: ArticleDatabase
            private set

        fun initDatabase(context: Context) {
           INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
package com.example.newspaper.data.database.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newspaper.data.database.dao.ArticleDao
import com.example.newspaper.data.database.dao.ArticleHistoryDao
import com.example.newspaper.data.database.dao.CategoryDao
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.data.database.models.ArticleHistory
import com.example.newspaper.data.database.models.Category

@Database(entities = [Article::class, ArticleHistory::class, Category::class], version = 11, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun historyDao(): ArticleHistoryDao
    abstract fun categoryDao(): CategoryDao

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
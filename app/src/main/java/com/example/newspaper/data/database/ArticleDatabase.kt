package com.example.newspaper.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Article::class, ArticleHistory::class, User::class, Category::class], version = 10, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun historyDao(): ArticleHistoryDao
    abstract fun userDao(): UserDao
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
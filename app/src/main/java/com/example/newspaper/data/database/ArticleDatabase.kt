package com.example.newspaper.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Article::class, ArticleHistory::class], version = 2, exportSchema = false)
@TypeConverters(ArticleConverter::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun historyDao(): ArticleHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        "article_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}
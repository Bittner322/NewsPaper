package com.example.newspaper.data.database.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newspaper.data.database.dao.ArticleDao
import com.example.newspaper.data.database.dao.CategoryDao
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.data.database.models.Category
import com.example.newspaper.data.repositories.models.CategoryCard

@Database(entities = [Article::class, Category::class], version = 14, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
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

        suspend fun fillDatabase() {
            val hasCategories = INSTANCE.categoryDao().getCategories().isNotEmpty()
            if (!hasCategories) {
                INSTANCE.categoryDao().insertAll(
                    CategoryCard.values().toList().map {
                        Category(
                            id = it.id,
                            categoryName = it.name.lowercase(),
                            isSelected = false,
                        )
                    }
                )
            }
        }
    }
}
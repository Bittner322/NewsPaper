package com.example.newspaper.data.database.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newspaper.data.database.dao.ArticleDao
import com.example.newspaper.data.database.dao.CategoryDao
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.data.database.models.Category
import com.example.newspaper.data.repositories.models.CategoryData

@Database(entities = [Article::class, Category::class], version = 14, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun categoryDao(): CategoryDao

    companion object {

        lateinit var INSTANCE: ArticleDatabase
            private set

        private val categoriesStartValues = listOf(
            CategoryData (
                id = 0,
                categoryName = "business",
                isSelected = false,
            ),
            CategoryData (
                id = 1,
                categoryName = "entertainment",
                isSelected = false,
            ),
            CategoryData (
                id = 2,
                categoryName = "general",
                isSelected = false,
            ),
            CategoryData (
                id = 3,
                categoryName = "health",
                isSelected = false,
            ),
            CategoryData (
                id = 4,
                categoryName = "science",
                isSelected = false,
            ),
            CategoryData (
                id = 5,
                categoryName = "sports",
                isSelected = false,
            ),
            CategoryData (
                id = 6,
                categoryName = "technology",
                isSelected = false,
            ),
        )


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
                    categoriesStartValues.map {
                        Category(
                            id = it.id,
                            categoryName = it.categoryName,
                            isSelected = it.isSelected,
                        )
                    }
                )
            }
        }
    }
}
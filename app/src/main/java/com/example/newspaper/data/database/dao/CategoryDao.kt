package com.example.newspaper.data.database.dao

import androidx.room.*
import com.example.newspaper.data.database.models.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(category: Category)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(categories: List<Category>)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT categoryName FROM Category")
    suspend fun getCategories(): List<String>

    @Query("DELETE FROM Category")
    suspend fun deleteAllCategoriesFromDatabase()
}
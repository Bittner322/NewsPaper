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

    @Query("SELECT * FROM Category")
    suspend fun getCategories(): List<Category>

    @Query("SELECT categoryName FROM Category WHERE is_selected = 1")
    suspend fun getChosenCategoriesForRequest(): List<String>

    @Query("DELETE FROM Category")
    suspend fun deleteAllCategoriesFromDatabase()

    @Query("UPDATE Category SET is_selected = 1 WHERE id = :id")
    suspend fun setCategoryIsSelected(id: Int)

    @Query("UPDATE Category SET is_selected = 0 WHERE id = :id")
    suspend fun setCategoryIsNotSelected(id: Int)

    @Query("SELECT is_selected FROM Category WHERE categoryName = :categoryName")
    suspend fun getChosenCategory(categoryName: String): Boolean

    @Query("SELECT COUNT(id) FROM Category WHERE is_selected = 1")
    suspend fun getCountOfSelectedCategories(): Int

}
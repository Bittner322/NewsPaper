package com.example.newspaper.data.repositories.models

import androidx.annotation.StringRes
import com.example.newspaper.R

enum class CategoryCard(
    val id: Int,
    @StringRes val categoryName: Int,
) {
    BUSINESS (
        id = 0,
        categoryName = R.string.category_business
    ),
    ENTERTAINMENT (
        id = 1,
        categoryName = R.string.category_entertainment
    ),
    GENERAL (
        id = 2,
        categoryName = R.string.category_general
    ),
    HEALTH (
        id = 3,
        categoryName = R.string.category_health
    ),
    SCIENCE (
        id = 4,
        categoryName = R.string.category_science
    ),
    SPORTS (
        id = 5,
        categoryName = R.string.category_sports
    ),
    TECHNOLOGY (
        id = 6,
        categoryName = R.string.category_technology
    );

    companion object {
        fun getCategoryNameById(id: Int): Int {
            return values().first { it.id == id }.categoryName
        }
    }
}
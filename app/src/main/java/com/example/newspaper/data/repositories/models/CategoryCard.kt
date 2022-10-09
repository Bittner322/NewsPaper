package com.example.newspaper.data.repositories.models

import androidx.annotation.StringRes
import com.example.newspaper.R

enum class CategoryCard(
    @StringRes val categoryName: Int,
) {
    BUSINESS (
       categoryName = R.string.category_business
    ),
    ENTERTAINMENT (
        categoryName = R.string.category_entertainment
    ),
    GENERAL (
        categoryName = R.string.category_general
    ),
    HEALTH (
        categoryName = R.string.category_health
    ),
    SCIENCE (
        categoryName = R.string.category_science
    ),
    SPORTS (
        categoryName = R.string.category_sports
    ),
    TECHNOLOGY (
        categoryName = R.string.category_technology
    ),
}
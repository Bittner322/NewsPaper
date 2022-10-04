package com.example.newspaper.data.repositories.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newspaper.R

enum class ProfileCard(
    @StringRes val cardNameRes: Int,
    @DrawableRes val iconRes: Int
) {
    FAVOURITES(
        cardNameRes = R.string.profile_item_my_favourites,
        iconRes =  R.drawable.ic_favourite
    ),
    HISTORY(
        cardNameRes =R.string.profile_item_history,
        iconRes = R.drawable.ic_history
    ),
    CATEGORIES(
        cardNameRes = R.string.profile_item_my_news_categories,
        iconRes = R.drawable.ic_category
    ),
    FAQ(
        cardNameRes = R.string.profile_item_faq,
        iconRes =  R.drawable.ic_faq
    )
}
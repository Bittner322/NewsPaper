package com.example.newspaper.data.repositories

import com.example.newspaper.data.database.databases.ArticleDatabase
import com.example.newspaper.data.repositories.models.ProfileCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    val articleDatabase: ArticleDatabase
) {

    suspend fun getCards(): List<ProfileCard> {
        return withContext(Dispatchers.IO) {
            ProfileCard.values().toList()
        }
    }
}
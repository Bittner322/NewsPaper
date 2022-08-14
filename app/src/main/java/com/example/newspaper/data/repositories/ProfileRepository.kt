package com.example.newspaper.data.repositories

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.repositories.models.ProfileCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository {

    private val articleDatabase = ArticleDatabase.INSTANCE

    private val userDao = articleDatabase.userDao()

    suspend fun getCards(): List<ProfileCard> {
        return withContext(Dispatchers.IO) {
            ProfileCard.values().toList()
        }
    }

    suspend fun setUserImage(id: Int, image: String) {
        withContext(Dispatchers.IO) {
            userDao.setUserImage(id = id, image = image)
        }
    }

}
package com.example.newspaper.data.repositories

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.database.User
import com.example.newspaper.data.repositories.models.ProfileCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val DEFAULT_USERNAME = "John Doe"
private const val DEFAULT_IMAGE = ""

class ProfileRepository(
    val articleDatabase: ArticleDatabase
) {

    suspend fun getCards(): List<ProfileCard> {
        return withContext(Dispatchers.IO) {
            ProfileCard.values().toList()
        }
    }

    suspend fun setUsername(username: String) {
        withContext(Dispatchers.IO) {
            articleDatabase.userDao().setUsername( username)
        }
    }

    suspend fun getUsername(): String {
        return withContext(Dispatchers.IO) {
            articleDatabase.userDao().getUsername()
        }
    }

    suspend fun setNewUser() {
        withContext(Dispatchers.IO) {
            articleDatabase.userDao().add(User(isCurrentUser = true, userImage = DEFAULT_IMAGE, username = DEFAULT_USERNAME))
        }
    }

}
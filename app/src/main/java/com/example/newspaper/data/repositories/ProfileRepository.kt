package com.example.newspaper.data.repositories

import com.example.newspaper.data.repositories.models.ProfileCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository {

    suspend fun getCards(): List<ProfileCard> {
        return withContext(Dispatchers.IO) {
            ProfileCard.values().toList()
        }
    }
}
package com.example.newspaper.data

import com.example.newspaper.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository {

    private val card1 = ProfileCard("My favourites", R.drawable.favorite)
    private val card2 = ProfileCard("History", R.drawable.history)
    private val card3 = ProfileCard("Your likes stats", R.drawable.like)
    private val card4 = ProfileCard("Offline data", R.drawable.offline)
    private val card5 = ProfileCard("FAQ", R.drawable.faq)

    suspend fun getCards(): List<ProfileCard> {
        return withContext(Dispatchers.IO) {
            listOf(card1, card2, card3, card4, card5)
        }
    }

}
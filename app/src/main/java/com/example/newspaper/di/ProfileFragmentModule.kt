package com.example.newspaper.di

import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.presentation.profile.ProfileViewModelFactory

class ProfileFragmentModule(
    databaseModule: DatabaseModule,
) {

    private val profileRepository = ProfileRepository(
        articleDatabase = databaseModule.articleDatabase
    )

    val viewModelFactory = ProfileViewModelFactory(
        repository = profileRepository
    )
}
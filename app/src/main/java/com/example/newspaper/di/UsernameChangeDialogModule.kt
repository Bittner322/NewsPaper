package com.example.newspaper.di

import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.presentation.dialogs.UsernameChangeDialogModelFactory

class UsernameChangeDialogModule(
    databaseModule: DatabaseModule,
) {

    private val profileRepository = ProfileRepository(
        articleDatabase = databaseModule.articleDatabase
    )

    val viewModelFactory = UsernameChangeDialogModelFactory(
        repository = profileRepository
    )
}


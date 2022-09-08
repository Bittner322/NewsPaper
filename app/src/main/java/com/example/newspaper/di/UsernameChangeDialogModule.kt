package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.presentation.dialogs.UsernameChangeDialogModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class UsernameChangeDialogModule{



    fun provideViewModelFactory(profileRepository: ProfileRepository): UsernameChangeDialogModelFactory {
        return UsernameChangeDialogModelFactory(
            repository = profileRepository
        )
    }
}


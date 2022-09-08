package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.presentation.profile.ProfileViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class ProfileFragmentModule {



    @Provides
    fun provideViewModelFactory(profileRepository: ProfileRepository): ProfileViewModelFactory {
        return ProfileViewModelFactory(
            repository = profileRepository
        )
    }
}
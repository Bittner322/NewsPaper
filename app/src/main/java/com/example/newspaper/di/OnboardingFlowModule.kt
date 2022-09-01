package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.presentation.onboarding_flow.OnboardingFlowViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class OnboardingFlowModule {

    @Provides
    fun provideProfileRepository(articleDatabase: ArticleDatabase): ProfileRepository {
        return ProfileRepository(
            articleDatabase = articleDatabase
        )
    }

    @Provides
    fun provideViewModelFactory(profileRepository: ProfileRepository): OnboardingFlowViewModelFactory {
        return OnboardingFlowViewModelFactory(
            repository = profileRepository
        )
    }
}
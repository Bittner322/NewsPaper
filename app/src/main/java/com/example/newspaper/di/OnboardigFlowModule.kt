package com.example.newspaper.di

import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.presentation.onboarding_flow.OnboardingFlowViewModelFactory

class OnboardigFlowModule(
    databaseModule: DatabaseModule,
) {

    private val profileRepository = ProfileRepository(
        articleDatabase = databaseModule.articleDatabase
    )

    val viewModelFactory = OnboardingFlowViewModelFactory(
        repository = profileRepository
    )
}
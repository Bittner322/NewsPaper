package com.example.newspaper.presentation.onboarding_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newspaper.data.repositories.ProfileRepository
import javax.inject.Inject

class OnboardingFlowViewModel : ViewModel()

class OnboardingFlowViewModelFactory @Inject constructor(
    private val repository: ProfileRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingFlowViewModel() as T
    }
}

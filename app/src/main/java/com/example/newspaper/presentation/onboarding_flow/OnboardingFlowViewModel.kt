package com.example.newspaper.presentation.onboarding_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.repositories.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingFlowViewModel(
    private val repository: ProfileRepository
): ViewModel() {

}

class OnboardingFlowViewModelFactory @Inject constructor(
    private val repository: ProfileRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingFlowViewModel(repository) as T
    }
}

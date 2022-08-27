package com.example.newspaper.presentation.onboarding_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.presentation.news.NewsViewModel
import kotlinx.coroutines.launch

class OnboardingFlowViewModel(
    private val repository: ProfileRepository
): ViewModel() {

    fun setNewUser() {
        viewModelScope.launch {
            repository.setNewUser()
        }
    }
}
class OnboardingFlowViewModelFactory(
    private val repository: ProfileRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingFlowViewModel(repository) as T
    }
}

package com.example.newspaper.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.repositories.ProfileRepository
import com.example.newspaper.data.repositories.models.ProfileCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
): ViewModel() {

    private val _cards = MutableStateFlow<List<ProfileCard>>(emptyList())
    val cards = _cards.asStateFlow()

    val usernameStateFlow = MutableStateFlow("John Doe")

    init {
        viewModelScope.launch {
            val response = repository.getCards()
            _cards.emit(response)
            initUsername()
        }
    }

    private fun initUsername() {
        viewModelScope.launch {
            val username = repository.getUsername()
            usernameStateFlow.update { username }
        }
    }
}

class ProfileViewModelFactory(
    private val repository: ProfileRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}
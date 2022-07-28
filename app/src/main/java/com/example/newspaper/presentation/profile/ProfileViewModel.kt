package com.example.newspaper.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.repositories.models.ProfileCard
import com.example.newspaper.data.repositories.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _cards = MutableStateFlow<List<ProfileCard>>(emptyList())
    val cards = _cards.asStateFlow()

    private val repository = ProfileRepository()

    init {
        viewModelScope.launch {
            val response = repository.getCards()
            _cards.emit(response)
        }
    }

}
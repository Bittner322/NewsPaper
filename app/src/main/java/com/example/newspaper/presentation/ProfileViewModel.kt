package com.example.newspaper.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.NewsRepository
import com.example.newspaper.data.NewsResponse
import com.example.newspaper.data.ProfileCard
import com.example.newspaper.data.ProfileRepository
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
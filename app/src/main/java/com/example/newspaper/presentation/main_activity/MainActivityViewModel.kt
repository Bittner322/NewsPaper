package com.example.newspaper.presentation.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    private val repository = NewsRepository()

    init {
        viewModelScope.launch {
            repository.loadAllArticlesIntoDatabase()
        }
    }
}
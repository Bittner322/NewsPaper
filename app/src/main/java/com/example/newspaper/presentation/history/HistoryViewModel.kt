package com.example.newspaper.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val historyArticles = _articles.asStateFlow()

    private val repository = NewsRepository()

    init {
        viewModelScope.launch {
            _articles.emit(repository.getHistoryArticles())
        }
    }
}

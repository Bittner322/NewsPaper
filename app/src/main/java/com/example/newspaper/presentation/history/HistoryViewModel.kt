package com.example.newspaper.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel(
    private val repository: NewsRepository
): ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val historyArticles = _articles.asStateFlow()



    init {
        viewModelScope.launch {
            _articles.emit(repository.getHistoryArticles())
        }
    }

    fun onClearHistoryClick() {
       viewModelScope.launch {
           repository.clearHistory()
           _articles.tryEmit(emptyList())
       }
    }
}

class HistoryActivityModelFactory @Inject constructor(
    private val repository: NewsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(repository) as T
    }
}

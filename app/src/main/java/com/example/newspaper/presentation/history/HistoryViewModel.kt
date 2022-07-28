package com.example.newspaper.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.MyApplication
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel: ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val historyArticles = _articles.asStateFlow()

    private val articleDatabase = ArticleDatabase.getInstance(MyApplication.applicationContext())

    private val repository = NewsRepository()

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

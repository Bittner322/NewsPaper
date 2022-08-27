package com.example.newspaper.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
): ViewModel() {



    private val _newsFlow = repository.getNewsFlow()
    val newsFlow = _newsFlow.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _isNewsLoadingFromNetworkFlow = MutableStateFlow(true)
    private val isNewsLoadingFromNetworkFlow = _isNewsLoadingFromNetworkFlow.asStateFlow()

    val isProgressBarVisible = combine(isNewsLoadingFromNetworkFlow, newsFlow) { isNewsLoadingFromNetwork, news ->
        isNewsLoadingFromNetwork && news.isEmpty()
    }.stateIn(viewModelScope, SharingStarted.Lazily, false)

    init {
        loadNewsFromNetwork()
    }

    private fun loadNewsFromNetwork() {
        viewModelScope.launch {
            _isNewsLoadingFromNetworkFlow.update { true }
            repository.loadAllArticlesIntoDatabase()
            _isNewsLoadingFromNetworkFlow.update { false }
        }
    }

    fun addArticleToHistory(article: Article) {
        viewModelScope.launch {
            repository.addItemToHistory(article)
        }
    }

    fun setArticleFavorite(article: Article) {
        viewModelScope.launch {
            repository.setArticleFavorite(article)
        }
    }

    fun setArticleNonFavorite(article: Article) {
        viewModelScope.launch {
            repository.setArticleNonFavorite(article)
        }
    }

}

class NewsViewModelFactory(
    private val repository: NewsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}
package com.example.newspaper.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel(
    private val repository: NewsRepository
): ViewModel() {

    private val _newsFlow = MutableStateFlow(emptyList<Article>())
    val newsFlow = _newsFlow.asStateFlow()

    private val _isNewsLoadingFromNetworkFlow = MutableStateFlow(true)
    private val isNewsLoadingFromNetworkFlow = _isNewsLoadingFromNetworkFlow.asStateFlow()

    val isProgressBarVisible = combine(isNewsLoadingFromNetworkFlow, newsFlow) { isNewsLoadingFromNetwork, news ->
        isNewsLoadingFromNetwork && news.isEmpty()
    }.stateIn(viewModelScope, SharingStarted.Lazily, false)

    init {
        repository.getNewsFlow()
            .onEach { _newsFlow.value = it }
            .launchIn(viewModelScope)
        loadNewsFromNetwork()
    }

    private fun loadNewsFromNetwork() {
        viewModelScope.launch {
            _isNewsLoadingFromNetworkFlow.update { true }
            repository.loadCategorizedNewsIntoDatabase()
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

    /*fun getNewsBySearchQuery(query: String) {
        viewModelScope.launch {
            repository.getNewsBySearchQuery(query)
                .onSuccess {
                    _newsFlow.value = it
                }
                .onFailure {
                    //
                }
        }
    }*/

    fun getNewsBySearchQuery(query: String) {
        viewModelScope.launch {
            repository.loadAllArticlesIntoDatabase(query)
        }
    }

}

class NewsViewModelFactory @Inject constructor(
    private val repository: NewsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}
package com.example.newspaper.presentation.full_article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.MyApplication
import com.example.newspaper.R
import com.example.newspaper.data.database.ArticleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FullArticleViewModel(
    private val articleId: Int
): ViewModel() {

    private val database = ArticleDatabase.getInstance(MyApplication.applicationContext())

    val titleStateFlow = MutableStateFlow("")
    val authorStateFlow = MutableStateFlow("")
    val dateStateFlow = MutableStateFlow("")
    val descriptionStateFlow = MutableStateFlow("")
    val urlToImageStateFlow = MutableStateFlow<Any>(R.drawable.if_image_null2)

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val article = database.articleDao().getArticleById(articleId)

            titleStateFlow.update { article.title }

            authorStateFlow.update { article.author }

            dateStateFlow.update { article.publishedAt.substring(0, 10) }

            descriptionStateFlow.update { article.content.take(200) }

            urlToImageStateFlow.update { article.urlToImage }
        }
    }
}

class FullArticleViewModelFactory(
    private val articleId: Int
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FullArticleViewModel(articleId) as T
    }
}
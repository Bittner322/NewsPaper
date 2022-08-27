package com.example.newspaper.presentation.full_article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.R
import com.example.newspaper.data.database.ArticleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FullArticleViewModel(
    private val url: String,
    private val database: ArticleDatabase
): ViewModel() {

    val titleStateFlow = MutableStateFlow("")
    val authorStateFlow = MutableStateFlow("")
    val dateStateFlow = MutableStateFlow("")
    val descriptionStateFlow = MutableStateFlow("")
    val urlToImageStateFlow = MutableStateFlow<Any>(R.drawable.if_image_null)

    init {
        loadData()
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return format.format(date).toString()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val article = database.articleDao().getArticleById(url)

            titleStateFlow.update { article.title }

            authorStateFlow.update { article.author }

            dateStateFlow.update { convertLongToTime(article.publishedAt).substring(0,10) }

            descriptionStateFlow.update { article.content.take(200) }

            urlToImageStateFlow.update { article.urlToImage }
        }
    }
}

class FullArticleViewModelFactory(
    private val url: String,
    private val database: ArticleDatabase
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FullArticleViewModel(url, database) as T
    }
}
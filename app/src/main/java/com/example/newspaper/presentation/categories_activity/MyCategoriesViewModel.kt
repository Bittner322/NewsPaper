package com.example.newspaper.presentation.categories_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.data.repositories.models.CategoryCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyCategoriesViewModel(
    private val repository: NewsRepository
): ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryCard>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        viewModelScope.launch {
            val categoryList = repository.getCategoryCards()
            _categories.emit(categoryList)
        }
    }

    fun setCategoryIsSelected(id: Int) {
        viewModelScope.launch {
            repository.setCategoryIsSelected(id)
        }
    }

    fun setCategoryIsNotSelected(id: Int) {
        viewModelScope.launch {
            repository.setCategoryIsNotSelected(id)
        }
    }
}

class MyCategoriesActivityViewModelFactory @Inject constructor(
    private val repository: NewsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyCategoriesViewModel(repository) as T
    }
}
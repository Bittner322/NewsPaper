package com.example.newspaper.presentation.onboarding_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.database.models.Category
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.data.repositories.models.CategoryCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryFragmentViewModel(
    private val repository: NewsRepository
): ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryCard>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        viewModelScope.launch {
            val categoryList = repository.getCategories()
            _categories.emit(categoryList)
        }
    }

    fun addCategoryIntoDatabase(category: Category) {
        viewModelScope.launch {
            repository.addCategoryIntoDatabase(category)
        }
    }

    fun deleteCategoryFromDatabase(category: Category) {
        viewModelScope.launch {
            repository.deleteCategoryFromDatabase(category)
        }
    }
}

class CategoryFragmentViewModelFactory @Inject constructor(
    private val repository: NewsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryFragmentViewModel(repository) as T
    }
}
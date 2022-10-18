package com.example.newspaper.presentation.onboarding_flow

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.MyApplication
import com.example.newspaper.R
import com.example.newspaper.data.database.databases.ArticleDatabase
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.data.repositories.models.CategoryData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val FIRST_LAUNCH = "firstLaunch"
private const val PREFS = "prefs"

class CategoryFragmentViewModel(
    private val repository: NewsRepository
): ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryData>>(emptyList())
    val categories = _categories.asStateFlow()

    private val eventChannel = Channel<CategoryEvent>(
        capacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow = eventChannel.receiveAsFlow()

    private val sharedPref = MyApplication.applicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)


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

    private fun deleteAllCategoriesFromDatabase() {
        viewModelScope.launch {
            repository.deleteAllCategoriesFromDatabase()
        }
    }

    private suspend fun getCountOfSelectedCategoriesFromDatabase(): Int {
        return repository.getCountOfSelectedCategories()
    }

    private fun refillDatabase() {
        viewModelScope.launch {
            ArticleDatabase.fillDatabase()
        }
    }

    fun onStartButtonClicked() {
        viewModelScope.launch {
            if(getCountOfSelectedCategoriesFromDatabase() == 0) {
                eventChannel.send(CategoryEvent.ShowToast(R.string.toast_you_did_not_picked_any_category))
            }
            else {

                sharedPref.edit {
                    putBoolean(FIRST_LAUNCH, false)
                }

                eventChannel.send(CategoryEvent.OpenMainActivity)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        if(!sharedPref.contains(FIRST_LAUNCH)) {
            deleteAllCategoriesFromDatabase()
            refillDatabase()
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
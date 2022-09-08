package com.example.newspaper.presentation.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.repositories.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsernameChangeDialogViewModel(
   private val repository: ProfileRepository
): ViewModel() {

    fun setUsername(username: String) {
        viewModelScope.launch {
            repository.setUsername(username = username)
        }
    }
}
class UsernameChangeDialogModelFactory @Inject constructor(
    private val repository: ProfileRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsernameChangeDialogViewModel(repository) as T
    }
}
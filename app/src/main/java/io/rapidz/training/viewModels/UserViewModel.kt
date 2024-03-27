package io.rapidz.training.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rapidz.training.models.User
import io.rapidz.training.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun dropUserTable() = viewModelScope.launch {
        repository.dropUserTable()
    }

    fun checkIfUserExists(username: String): Boolean {
        return repository.checkIfUserExists(username)
    }
}
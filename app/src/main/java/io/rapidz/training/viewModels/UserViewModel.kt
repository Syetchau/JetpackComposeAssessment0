package io.rapidz.training.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rapidz.training.models.User
import io.rapidz.training.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.security.auth.callback.Callback

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun insertUser(user: User, callback: () -> Unit) = viewModelScope.launch {
        repository.insertUser(user, callback)
    }

    fun dropUserTable() = viewModelScope.launch {
        repository.dropUserTable()
    }

    fun getUserPassword(username: String): LiveData<String?> {
        return repository.getUserPassword(username)
    }
}
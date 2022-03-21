package com.example.secondroom.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.secondroom.db.User
import kotlinx.coroutines.launch
import com.example.secondroom.db.repository.UserRepository
import java.lang.IllegalArgumentException


class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allWords: LiveData<List<User>> = repository.allUsers

    fun insert(word: User) = viewModelScope.launch {
        repository.insert(word)
    }

}

class UserViewModelFactory (private val repository: UserRepository): ViewModelProvider.Factory{

    override fun <T: ViewModel> create (modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
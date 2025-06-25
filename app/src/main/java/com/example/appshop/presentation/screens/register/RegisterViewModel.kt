package com.example.appshop.presentation.screens.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appshop.dao.DatabaseProvider
import com.example.appshop.dao.User
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val userDao = DatabaseProvider.getDatabase(application).userDao()

    fun registerUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val user = User(
                name = fullName,
                email = email,
                password = password
            )
            userDao.insertUser(user)
            onSuccess()
        }
    }
}

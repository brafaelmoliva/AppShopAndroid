package com.example.appshop.presentation.screens.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appshop.data.remote.RetrofitClient
import com.example.appshop.domain.model.Local
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocalesViewModel : ViewModel() {
    private val _locales = MutableStateFlow<List<Local>>(emptyList())
    val locales: StateFlow<List<Local>> = _locales

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getLocales()
                _locales.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

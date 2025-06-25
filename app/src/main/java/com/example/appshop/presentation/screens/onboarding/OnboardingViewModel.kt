package com.example.appshop.presentation.screens.onboarding


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingViewModel : ViewModel() {
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

    fun setCurrentPage(index: Int) {
        _currentPage.value = index
    }
}

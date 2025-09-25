package com.home.petprojectv2.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    init {
        loadData()
    }

    private fun loadData() {
        // Start your background task
        viewModelScope.launch {
            // Simulate a network call or heavy computation
            delay(2000)
            _isDataLoaded.value = true
        }
    }
}

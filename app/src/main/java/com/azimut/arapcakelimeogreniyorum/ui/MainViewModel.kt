package com.azimut.arapcakelimeogreniyorum.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import com.azimut.arapcakelimeogreniyorum.ui.navigation.AppBottomTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * View model handling root navigation tab switching and seeding initialization.
 */
class MainViewModel(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    private val _currentTab = MutableStateFlow(AppBottomTab.DASHBOARD)
    val currentTab: StateFlow<AppBottomTab> = _currentTab.asStateFlow()

    private val _isInitialized = MutableStateFlow(false)
    val isInitialized: StateFlow<Boolean> = _isInitialized.asStateFlow()

    init {
        viewModelScope.launch {
            repository.ensureDataSeeded()
            _isInitialized.value = true
        }
    }

    fun selectTab(tab: AppBottomTab) {
        _currentTab.value = tab
    }

    class Factory(private val repository: ArabicLearningRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

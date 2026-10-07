package com.azimut.arapcakelimeogreniyorum.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.azimut.arapcakelimeogreniyorum.data.domain.UserProgressCalculator
import com.azimut.arapcakelimeogreniyorum.data.domain.UserProgressStats
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class DashboardUiState(
    val alphabetTotal: Int = 28,
    val alphabetCompleted: Int = 0,
    val vocabularyTotal: Int = 0,
    val vocabularyMastered: Int = 0,
    val userStats: UserProgressStats = UserProgressStats(),
    val isLoading: Boolean = false
) {
    val placementTestScore: Int?
        get() = userStats.placementScore

    val userLevelTitle: String
        get() = userStats.currentLevel.fullDisplayTitle
}

class DashboardViewModel(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = combine(
        repository.getAllAlphabet(),
        repository.getAllVocabulary(),
        repository.getAllProgress()
    ) { alphabetList, vocabularyList, progressList ->

        val alphabetCompletedCount = progressList
            .filter { it.itemType == "ALPHABET" && it.isCompleted }
            .map { it.itemId }
            .distinct()
            .size

        val masteredVocabCount = vocabularyList.count { it.masteryLevel >= 3 }
        val calculatedStats = UserProgressCalculator.calculateStats(progressList)

        DashboardUiState(
            alphabetTotal = alphabetList.size.ifZero(28),
            alphabetCompleted = alphabetCompletedCount,
            vocabularyTotal = vocabularyList.size,
            vocabularyMastered = masteredVocabCount,
            userStats = calculatedStats,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardUiState(isLoading = true)
    )

    class Factory(private val repository: ArabicLearningRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
                return DashboardViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

private fun Int.ifZero(default: Int): Int = if (this == 0) default else this

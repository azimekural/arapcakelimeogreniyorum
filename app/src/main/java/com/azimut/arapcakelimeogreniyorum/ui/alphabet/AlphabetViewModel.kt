package com.azimut.arapcakelimeogreniyorum.ui.alphabet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.UserProgressEntity
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AlphabetTab {
    LETTERS,
    DIACRITICS
}

data class AlphabetUiState(
    val selectedTab: AlphabetTab = AlphabetTab.LETTERS,
    val letters: List<AlphabetEntity> = emptyList(),
    val diacritics: List<DiacriticEntity> = emptyList(),
    val selectedLetter: AlphabetEntity? = null,
    val selectedDiacritic: DiacriticEntity? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false
)

class AlphabetViewModel(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(AlphabetTab.LETTERS)
    private val _selectedLetter = MutableStateFlow<AlphabetEntity?>(null)
    private val _selectedDiacritic = MutableStateFlow<DiacriticEntity?>(null)
    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<AlphabetUiState> = combine(
        combine(_selectedTab, _selectedLetter, _selectedDiacritic, _searchQuery) { tab, letter, diacritic, query ->
            UiParams(tab, letter, diacritic, query)
        },
        repository.getAllAlphabet(),
        repository.getAllDiacritics()
    ) { params, letters, diacritics ->

        val query = params.query
        val filteredLetters = if (query.isBlank()) letters else {
            letters.filter {
                it.nameTurkish.contains(query, ignoreCase = true) ||
                        it.letterArabic.contains(query) ||
                        it.transliteration.contains(query, ignoreCase = true)
            }
        }

        val filteredDiacritics = if (query.isBlank()) diacritics else {
            diacritics.filter {
                it.nameTurkish.contains(query, ignoreCase = true) ||
                        it.nameArabic.contains(query)
            }
        }

        AlphabetUiState(
            selectedTab = params.tab,
            letters = filteredLetters,
            diacritics = filteredDiacritics,
            selectedLetter = params.letter,
            selectedDiacritic = params.diacritic,
            searchQuery = query,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AlphabetUiState(isLoading = true)
    )

    fun selectTab(tab: AlphabetTab) {
        _selectedTab.value = tab
    }

    fun selectLetter(letter: AlphabetEntity?) {
        _selectedLetter.value = letter
    }

    fun selectDiacritic(diacritic: DiacriticEntity?) {
        _selectedDiacritic.value = diacritic
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun markLetterReviewed(letterId: Int) {
        viewModelScope.launch {
            repository.saveUserProgress(
                UserProgressEntity(
                    itemType = "ALPHABET",
                    itemId = letterId,
                    isCompleted = true,
                    score = 100,
                    lastReviewedAt = System.currentTimeMillis()
                )
            )
        }
    }

    private data class UiParams(
        val tab: AlphabetTab,
        val letter: AlphabetEntity?,
        val diacritic: DiacriticEntity?,
        val query: String
    )

    class Factory(private val repository: ArabicLearningRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlphabetViewModel::class.java)) {
                return AlphabetViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

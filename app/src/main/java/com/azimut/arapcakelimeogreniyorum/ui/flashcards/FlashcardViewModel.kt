package com.azimut.arapcakelimeogreniyorum.ui.flashcards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FlashcardUiState(
    val cards: List<VocabularyEntity> = emptyList(),
    val currentIndex: Int = 0,
    val isFlipped: Boolean = false,
    val selectedCategory: String = "Tümü",
    val categories: List<String> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
) {
    val currentCard: VocabularyEntity?
        get() = if (cards.isNotEmpty() && currentIndex in cards.indices) cards[currentIndex] else null
}

class FlashcardViewModel(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    private val _currentIndex = MutableStateFlow(0)
    private val _isFlipped = MutableStateFlow(false)
    private val _selectedCategory = MutableStateFlow("Tümü")
    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<FlashcardUiState> = combine(
        combine(_currentIndex, _isFlipped, _selectedCategory, _searchQuery) { idx, flipped, category, query ->
            UiParams(idx, flipped, category, query)
        },
        repository.getAllVocabulary(),
        repository.getAllVocabularyCategories()
    ) { params, vocabularyList, dbCategories ->

        val allCategories = listOf("Tümü") + dbCategories

        val filteredByCategory = if (params.category == "Tümü") vocabularyList else {
            vocabularyList.filter { it.category.equals(params.category, ignoreCase = true) }
        }

        val filteredCards = if (params.query.isBlank()) filteredByCategory else {
            filteredByCategory.filter {
                it.turkishMeaning.contains(params.query, ignoreCase = true) ||
                        it.arabicText.contains(params.query) ||
                        it.transliteration.contains(params.query, ignoreCase = true)
            }
        }

        val adjustedIndex = if (filteredCards.isEmpty()) 0 else params.index.coerceIn(0, filteredCards.size - 1)

        FlashcardUiState(
            cards = filteredCards,
            currentIndex = adjustedIndex,
            isFlipped = params.isFlipped,
            selectedCategory = params.category,
            categories = allCategories,
            searchQuery = params.query,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FlashcardUiState(isLoading = true)
    )

    fun flipCard() {
        _isFlipped.value = !_isFlipped.value
    }

    fun nextCard() {
        val currentCards = uiState.value.cards
        if (currentCards.isNotEmpty()) {
            _currentIndex.value = (_currentIndex.value + 1) % currentCards.size
            _isFlipped.value = false
        }
    }

    fun previousCard() {
        val currentCards = uiState.value.cards
        if (currentCards.isNotEmpty()) {
            _currentIndex.value = if (_currentIndex.value - 1 < 0) currentCards.size - 1 else _currentIndex.value - 1
            _isFlipped.value = false
        }
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
        _currentIndex.value = 0
        _isFlipped.value = false
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _currentIndex.value = 0
        _isFlipped.value = false
    }

    fun toggleFavorite(card: VocabularyEntity) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(card.id, !card.isFavorite)
        }
    }

    fun updateMasteryLevel(cardId: Int, level: Int) {
        viewModelScope.launch {
            repository.updateMasteryLevel(cardId, level)
        }
    }

    private data class UiParams(
        val index: Int,
        val isFlipped: Boolean,
        val category: String,
        val query: String
    )

    class Factory(private val repository: ArabicLearningRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FlashcardViewModel::class.java)) {
                return FlashcardViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

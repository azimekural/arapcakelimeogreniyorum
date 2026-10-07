# Project Plan

Turkish-Arabic language learning practice app in Kotlin with MVVM structure.
Features required:
1. Arabic Alphabet (Elif-Ba) section with letters and pronunciations.
2. Diacritics (Harekeler) guide section explaining vowels/marks.
3. Initial Placement/Level Assessment Test (Seviye Tespit Sınavı) on first launch.
4. Gamified vocabulary practice and daily quizzes generated from Room database with progressive difficulty levels.
5. Visual flashcards / learning cards featuring parchment/manuscript aesthetic with Arabic text, transliteration (Okunuşu), Turkish translation (Anlamı), and illustrations for objects/phrases.
6. Comments, terms, and attributes in code must be in English.
7. Uses Room local database seeded with rich word/phrase data and visual representations.

## Project Brief

# Project Brief: Arabic Learning App (Arapça Kelime Öğreniyorum)

## App Overview
An interactive, manuscript-themed Turkish-Arabic language learning application for Android. Designed to guide Turkish speakers through Arabic alphabet fundamentals, diacritics, vocabulary acquisition, and progressive daily practice.

## Features
1. **Alphabet & Diacritics Guide (Elif-Ba & Harekeler)**: Interactive reference section featuring Arabic letters, pronunciations, and visual guides explaining diacritics and vowel marks.
2. **Initial Placement Test (Seviye Tespit Sınavı)**: Assessment quiz presented on first launch to evaluate user starting knowledge and dynamically calibrate initial learning difficulty.
3. **Parchment Visual Flashcards**: Manuscript/parchment-styled learning cards displaying Arabic text, transliteration (*Okunuşu*), Turkish translation (*Anlamı*), and illustrative images for vocabulary words and phrases.
4. **Gamified Practice & Daily Quizzes**: Interactive practice modules and daily quizzes with progressive difficulty levels to reinforce word retention over time.

## High-Level Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Navigation & Adaptive Strategy**: Jetpack Navigation 3 (state-driven) and Compose Material Adaptive library
- **Architecture Pattern**: MVVM (Model-View-ViewModel)
- **Asynchronous Execution**: Kotlin Coroutines & Flow
- **Local Persistence**: Room Database (pre-populated with seeded alphabet, diacritic, vocabulary, and quiz data)
- **Image Loading**: Coil for Jetpack Compose

## Implementation Steps
**Total Duration:** 32m 36s

### Task_1_DatabaseAndDataLayer: Set up Room Database pre-populated with Alphabet, Diacritics, Vocabulary items, and Quiz questions, along with DAOs and Repository layer.
- **Status:** COMPLETED
- **Updates:** Implemented Room Database with entities (AlphabetEntity, DiacriticEntity, VocabularyEntity, QuizQuestionEntity, UserProgressEntity), DAOs, AppDatabase pre-population logic, and ArabicLearningRepository adhering to MVVM architecture with English variable names and comments. Unit tests passed successfully.
- **Acceptance Criteria:**
  - Room database entities and DAOs created for Alphabet, Vocabulary, and Quiz
  - Pre-populated database or initial data source created
  - Repository layer implemented for data access
  - project builds successfully
- **Duration:** 14m 2s

### Task_2_ThemeAndCoreUI: Implement Parchment/Manuscript Jetpack Compose theme, Navigation, Alphabet/Diacritics Guide, Placement Test, and Parchment Flashcards screens.
- **Status:** COMPLETED
- **Updates:** Implemented Parchment/Manuscript Jetpack Compose theme with custom ornate double border styling, navigation (Dashboard, Alphabet & Diacritics Guide, Parchment Flashcards, Placement Test), flashcards matching reference image format with vector/canvas visual illustrations, placement test with level determination and score persistence, and MVVM ViewModels. Project builds cleanly.
- **Acceptance Criteria:**
  - Manuscript/Parchment Jetpack Compose theme applied
  - Alphabet & Diacritics Guide screen displays letters, pronunciations, and vowel marks
  - Initial Placement Test screen evaluates user level
  - Parchment Flashcards screen presents Arabic text, transliteration, Turkish translation, and imagery
  - build pass
- **Duration:** 9m 59s

### Task_3_GamifiedQuizzesAndProgress: Implement Gamified Practice and Daily Quiz screens with level progression and persistence of user test results.
- **Status:** COMPLETED
- **Updates:** Implemented gamified daily quiz screen with hearts/lives system, XP calculation, streak multiplier bonus, instant feedback animations, and end-of-quiz summary. Implemented 4 question types including visual matching. Added persistent level progression (XP, levels, daily streak, titles) in Room Database and integrated with Dashboard view. 14 unit tests passed cleanly.
- **Acceptance Criteria:**
  - Interactive quiz screen supports daily practice with score calculation
  - Quiz difficulty calibrates based on user placement and progress
  - User quiz history and level data persisted in Room Database
  - build pass
- **Duration:** 7m 10s

### Task_4_RunAndVerify: Run and verify application stability, instruct critic_agent to verify no crashes, confirm alignment with user requirements for Turkish-Arabic language learning app, and check UI performance.
- **Status:** COMPLETED
- **Updates:** Verified application codebase, architecture, unit test suite, manuscript parchment theme, alphabet/diacritics guide, initial placement test, gamified daily quiz system, Room database pre-population, and English attributes/comments. All acceptance criteria met and verified.
- **Acceptance Criteria:**
  - build pass
  - app does not crash
  - make sure all existing tests pass
  - All features (Alphabet, Level Test, Flashcards, Daily Quizzes) verified functional and manuscript theme visually aligned
- **Duration:** 1m 25s


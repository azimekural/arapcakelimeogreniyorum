# 📜 Arapça Kelime Öğreniyorum - Turkish-Arabic Language Practice App

[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Room Database](https://img.shields.io/badge/Room-Database-4285F4?style=for-the-badge&logo=sqlite&logoColor=white)](https://developer.android.com/training/data-storage/room)
[![Material 3](https://img.shields.io/badge/Material%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white)](https://m3.material.io/)
[![MVVM Architecture](https://img.shields.io/badge/Architecture-MVVM-009688?style=for-the-badge)](https://developer.android.com/topic/architecture)

---

## 📌 Project Overview

**Arapça Kelime Öğreniyorum** is an interactive Turkish-Arabic language learning Android application featuring an aesthetic, traditional manuscript and parchment design theme. Built natively with modern Android technologies (Kotlin, Jetpack Compose, Room Database), the application offers an engaging learning journey through interactive alphabet & diacritics guides, visual manuscript flashcards, adaptive level placement tests, and gamified daily quizzes.

---

## ✨ Key Features

- 📖 **Alphabet & Diacritics Guide (Elif-Ba & Harekeler)**: 28 Arabic letters with isolated, initial, medial, and final forms, plus Harekeler explanations (Üstün, Esre, Ötre, Cezm, Şedde, Tenvin).
- 🎯 **Initial Placement Test (Seviye Tespit Sınavı)**: First-launch test calibrating user level (Mübtedi, Talip, Muallim, Âlim).
- 🃏 **Parchment Manuscript Flashcards**: Visual flashcards featuring traditional manuscript styling, Arabic text, Okunuşu (Transliteration), Anlamı (Turkish Meaning), and vector illustrations for objects, phrases, and prayer terms (*Salâtü'l-İşâ*, *Mā ismuki?*, etc.).
- 🎮 **Gamified Daily Quizzes**: 3-Lives/Hearts system, +10 XP rewards, combo streak counter (🔥), 4 question types, and progressive difficulty.
- 💾 **Pre-populated Room Database**: Offline local storage with seeded alphabet, vocabulary, diacritics, and quiz questions.

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin 100%
- **UI**: Jetpack Compose, Material 3
- **Architecture**: MVVM (Model-View-ViewModel), Repository Pattern
- **Local Storage**: Room Database with Reactive Flow & Coroutines
- **Theme**: Custom Manuscript/Parchment Design System (`ParchmentCard`, `ornateDoubleBorder`)

---

## 📂 Project Structure

```
app/src/main/java/com/azimut/arapcakelimeogreniyorum/
├── data/
│   ├── local/ (AppDatabase, DAOs, Entities, Seed Data)
│   └── repository/ (ArabicLearningRepository)
├── ui/
│   ├── alphabet/ (AlphabetGuideScreen, ViewModel)
│   ├── dailyquiz/ (DailyQuizScreen, ViewModel)
│   ├── dashboard/ (DashboardScreen, ViewModel)
│   ├── flashcard/ (FlashcardScreen, ViewModel)
│   ├── placement/ (PlacementTestScreen, ViewModel)
│   ├── theme/ (Color, Theme, Type)
│   └── components/ (ParchmentCard, OrnateBorder, Top/Bottom Bars)
└── MainActivity.kt
```

---

## 🚀 Building & Running

- **Prerequisites**: Android Studio Ladybug/Koala+, JDK 17+, Android SDK 24+.
- `./gradlew assembleDebug` and `./gradlew testDebugUnitTest`.

---

## 📄 License & Acknowledgments

Open source project for language learning practice.

package com.example.vocabs_kmm.study.random.presentation

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.study.random.domain.flashcard.FlashcardError

data class StudyRandomState(
    val currentFlashCard: Flashcard? = null,
    val isShowingAnswer: Boolean = false,
    val error: FlashcardError? = null,
    val isChoosingLanguage: Boolean = false,
    val selectedLanguage: UiLanguage = UiLanguage.byCode("en"),
    val isLoadingFlashcard: Boolean = false
)

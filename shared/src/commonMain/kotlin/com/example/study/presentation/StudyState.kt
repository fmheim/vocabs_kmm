package com.example.study.presentation

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard

data class StudyState(
    val currentFlashCard: Flashcard? = null,
    val isShowingAnswer: Boolean = false,
    val error: String? = null,
    val isChoosingLanguage: Boolean = false,
    val isLoadingFlashcard: Boolean = false
)

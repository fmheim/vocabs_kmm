package com.example.vocabs_kmm.study.all.presentation

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.study.random.domain.flashcard.FlashcardError

data class StudyAllState(

    val currentFlashCards: List<Flashcard>? = null,
    val error: FlashcardError? = null,
    val isChoosingLanguage: Boolean = false,
    val selectedLanguage: UiLanguage = UiLanguage.byCode("en"),
    val isLoadingFlashcards: Boolean = false
)

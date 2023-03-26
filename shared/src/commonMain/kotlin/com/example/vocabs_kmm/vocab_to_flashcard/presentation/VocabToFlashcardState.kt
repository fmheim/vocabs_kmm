package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.ExamplePhrase

data class VocabToFlashcardState(
    val selectedLanguage: UiLanguage = UiLanguage.byCode("en"),
    val vocabInput: String? = null,
    val submittedVocab: String? = null,
    val phrase: ExamplePhrase? = null,
    val isGenerating: Boolean = false,
    val isChoosingLanguage: Boolean = false,
    val lastSavedFlashCard: Flashcard? = null,
    val showSavedFlashcard: Boolean = false,
    val error: Exception? = null
)

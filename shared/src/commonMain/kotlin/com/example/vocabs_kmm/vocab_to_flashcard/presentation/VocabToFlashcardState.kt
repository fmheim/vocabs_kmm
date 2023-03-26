package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.ExamplePhrase
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseError

data class VocabToFlashcardState(
    val language: UiLanguage = UiLanguage.byCode("en"),
    val vocab: String? = null,
    val phrase: ExamplePhrase? = null,
    val isGenerating: Boolean = false,
    val isChoosingLanguage: Boolean = false,
    val vocabToPhraseError: VocabToPhraseError? = null
)

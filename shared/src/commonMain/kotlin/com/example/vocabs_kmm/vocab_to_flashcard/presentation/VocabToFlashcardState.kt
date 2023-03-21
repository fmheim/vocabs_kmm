package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseError

data class VocabToFlashcardState(
    val fromLanguage: String? = null,
    val toLanguage: String? = null,
    val vocab: String? = null,
    val phrase: String? = null,
    val isGenerating: Boolean = false,
    val vocabToPhraseError: VocabToPhraseError? = null
)

package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseError

sealed class VocabToFlashcardEvent() {
    object GeneratePhrase : VocabToFlashcardEvent()
    object SaveFlashcard : VocabToFlashcardEvent()

    data class GenerateImage(val imagePrompt: String) : VocabToFlashcardEvent()
    data class VocabInputChanged(val text: String) : VocabToFlashcardEvent()
    data class SelectLanguage(val language: UiLanguage) : VocabToFlashcardEvent()
    object OpenLanguageDropDown : VocabToFlashcardEvent()
    object CloseLanguageDropDown : VocabToFlashcardEvent()
    data class Error(val error: VocabToPhraseError) : VocabToFlashcardEvent()

}

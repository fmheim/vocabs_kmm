package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.presentation.UiLanguage

sealed class VocabToFlashcardEvent() {
    object GeneratePhrase : VocabToFlashcardEvent()
    object SaveFlashcard : VocabToFlashcardEvent()
    data class ImageDownloadFinished(val loadedImage: ByteArray): VocabToFlashcardEvent()


    object GenerateImage : VocabToFlashcardEvent()
    data class VocabInputChanged(val text: String) : VocabToFlashcardEvent()
    data class SelectLanguage(val language: UiLanguage) : VocabToFlashcardEvent()
    object OpenLanguageDropDown : VocabToFlashcardEvent()
    object CloseLanguageDropDown : VocabToFlashcardEvent()


    object ToStudyScreen : VocabToFlashcardEvent()
    object OnErrorSeen : VocabToFlashcardEvent()

}

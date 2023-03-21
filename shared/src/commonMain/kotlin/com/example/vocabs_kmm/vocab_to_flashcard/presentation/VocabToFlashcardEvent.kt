package com.example.vocabs_kmm.vocab_to_flashcard.presentation

sealed class VocabToFlashcardEvent(){
    data class GeneratePhrase(val fromLanguage: String, val toLanguage: String, val vocab: String): VocabToFlashcardEvent()
    data class GenerateImage(val imagePrompt: String): VocabToFlashcardEvent()

}

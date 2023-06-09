package com.example.vocabs_kmm.core.domain.flashcard

import com.example.vocabs_kmm.core.domain.util.CommonFlow

interface FlashcardDataSource {
    fun getAllFlashcards():CommonFlow<List<Flashcard>>
    fun getAllFlashcardsInCertainLanguage(languageCode: String): CommonFlow<List<Flashcard>>
    suspend fun getRandomFlashcardInCertainLanguage(languageCode: String): Flashcard?

    suspend fun insertFlashcard(flashcard: Flashcard)
}
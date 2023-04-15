package com.example.vocabs_kmm.core.data.flashcard

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.core.domain.util.CommonFlow
import com.example.vocabs_kmm.core.domain.util.toCommonFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeFlashcardDataSource : FlashcardDataSource {

    private val _flashcards = MutableStateFlow(listOf<Flashcard>())

    override fun getAllFlashcards(): CommonFlow<List<Flashcard>> {
        return _flashcards.toCommonFlow()
    }

    override fun getAllFlashcardsInCertainLanguage(languageCode: String): CommonFlow<List<Flashcard>> {
        return _flashcards.map { allCards ->
            allCards.filter { flashcard ->
                flashcard.languageCode == languageCode
            }
        }.toCommonFlow()
    }

    override suspend fun getRandomFlashcardInCertainLanguage(languageCode: String): Flashcard? {
        return _flashcards.value.random()
    }

    override suspend fun insertFlashcard(flashcard: Flashcard) {
        _flashcards.value += flashcard
    }
}
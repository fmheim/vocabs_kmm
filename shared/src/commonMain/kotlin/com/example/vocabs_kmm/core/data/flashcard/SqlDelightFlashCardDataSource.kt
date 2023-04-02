package com.example.vocabs_kmm.core.data.flashcard

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.core.domain.util.CommonFlow
import com.example.vocabs_kmm.core.domain.util.toCommonFlow
import com.example.vocabs_kmm.database.FlashcardDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightFlashCardDataSource(db: FlashcardDatabase) : FlashcardDataSource {

    private val queries = db.flashcardsQueries
    override fun getAllFlashcards(): CommonFlow<List<Flashcard>> {
        return queries.getAllFlashcards().asFlow().mapToList().map { flashcards ->
                flashcards.map { entity ->
                    entity.toFlashCard()
                }
            }.toCommonFlow()
    }

    override fun getAllFlashcardsInCertainLanguage(languageCode: String): CommonFlow<List<Flashcard>> {
        return queries.getAllFlashcardsInCertainLanguage(languageCode = languageCode).asFlow()
            .mapToList().map { flashcards ->
                flashcards.map { entity ->
                    entity.toFlashCard()
                }
            }.toCommonFlow()
    }

    override suspend fun getRandomFlashcardInCertainLanguage(languageCode: String): Flashcard? {
        return queries.getRandomFlashcardInCertainLanguage(languageCode)
            .executeAsOneOrNull()
            ?.toFlashCard()
    }

    override suspend fun insertFlashcard(flashcard: Flashcard) {
        queries.insertFlashcard(
            id = flashcard.id,
            languageCode = flashcard.languageCode,
            vocab = flashcard.vocab,
            beforeVocabText = flashcard.beforeVocabText,
            vocabInPhrase = flashcard.vocabInPhrase,
            afterVocabText = flashcard.afterVocabText,
            timestamp = Clock.System.now().toEpochMilliseconds(),
            image = flashcard.image
        )
    }

}
package com.example.vocabs_kmm.study.random.domain.flashcard

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardException
import com.example.vocabs_kmm.core.domain.util.CommonFlow
import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.study.random.domain.flashcard.FlashcardError


class GetRandomFlashcard(private val flashcardDataSource: FlashcardDataSource) {

    suspend fun execute(languageCode: String): Resource<Flashcard> {

        return try {
            val flashCard =
                flashcardDataSource.getRandomFlashcardInCertainLanguage(languageCode = languageCode)
                    ?: throw FlashcardException(FlashcardError.N0_FLASHCARD_FOUND)
            Resource.Success(flashCard)
        } catch (e: com.example.vocabs_kmm.study.random.domain.flashcard.FlashcardException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}
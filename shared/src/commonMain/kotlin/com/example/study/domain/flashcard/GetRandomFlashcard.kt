package com.example.study.domain.flashcard

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.core.domain.util.CommonFlow
import com.example.vocabs_kmm.core.domain.util.Resource
import io.ktor.utils.io.errors.IOException

class GetRandomFlashcard(private val flashcardDataSource: FlashcardDataSource) {

    fun execute(languageCode: String): Resource<CommonFlow<Flashcard>> {

        return try {
            Resource.Success(flashcardDataSource.getRandomFlashcardInCertainLanguage(languageCode = languageCode))
        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error(e)
        }
    }

}
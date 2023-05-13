package com.example.vocabs_kmm.study.all.domain

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.core.domain.util.CommonFlow
import com.example.vocabs_kmm.core.domain.util.Resource

class GetAllFlashcardsInCertainLanguage(private val flashcardDataSource: FlashcardDataSource) {

    fun execute(languageCode: String): Resource<CommonFlow<List<Flashcard>>>{
        return try {
            val flashcardsFlow =
            flashcardDataSource.getAllFlashcardsInCertainLanguage(languageCode = languageCode)
            Resource.Success(flashcardsFlow)

        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e)
        }
    }

}
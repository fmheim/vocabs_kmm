package com.example.vocabs_kmm.vocab_to_flashcard.domain.flashcard

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardError
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardException
import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.ExamplePhrase


class SaveAsFlashcard(private val flashcardDataSource: FlashcardDataSource) {
    suspend fun execute(
        languageCode: String?,
        examplePhrase: ExamplePhrase?,
        vocab: String?,
        image: ByteArray?
    ): Resource<Flashcard> {
        return try {
            if (languageCode == null) throw FlashcardException(error = FlashcardError.LANGUAGE_NOT_SPECIFIED)
            if (examplePhrase == null) throw FlashcardException(error = FlashcardError.MISSING_EXAMPLE_PHRASE)
            if (vocab == null) throw FlashcardException(error = FlashcardError.VOCAB_NOT_SPECIFIED)
            if (image == null) throw FlashcardException(error = FlashcardError.MISSING_IMAGE)
            val card = Flashcard(
                id = null,
                languageCode = languageCode,
                vocab = vocab,
                beforeVocabText = examplePhrase.beforeVocab,
                vocabInPhrase = examplePhrase.vocab,
                afterVocabText = examplePhrase.afterVocab,
                image = image
            )
            flashcardDataSource.insertFlashcard(
                flashcard = card
            )
            return Resource.Success(data = card)
        } catch (e: FlashcardException) {
            e.printStackTrace()
            Resource.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(FlashcardException(error = FlashcardError.UNKNOWN_ERROR))
        }
    }
}
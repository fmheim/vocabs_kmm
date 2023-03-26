package com.example.vocabs_kmm.core.data.flashcard

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import database.FlashcardEntity

fun FlashcardEntity.toFlashCard(): Flashcard {
    return Flashcard(
        id = id,
        languageCode = languageCode,
        vocab = vocab,
        beforeVocabText = beforeVocabText,
        vocabInPhrase = vocabInPhrase,
        afterVocabText = afterVocabText
    )
}
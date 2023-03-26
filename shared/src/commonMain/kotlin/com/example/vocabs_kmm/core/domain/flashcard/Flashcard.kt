package com.example.vocabs_kmm.core.domain.flashcard

data class Flashcard (
    val id: Long?,
    val languageCode: String,
    val vocab: String,
    val beforeVocabText: String,
    val vocabInPhrase: String,
    val afterVocabText: String
)
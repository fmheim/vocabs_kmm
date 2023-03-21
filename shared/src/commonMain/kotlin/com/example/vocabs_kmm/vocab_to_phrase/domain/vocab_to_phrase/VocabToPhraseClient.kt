package com.example.vocabs_kmm.vocab_to_phrase.domain.vocab_to_phrase


interface VocabToPhraseClient {
    suspend fun generatePhrase(
        fromLanguage: String,
        toLanguage: String,
        fromWord: String
    ): String
}
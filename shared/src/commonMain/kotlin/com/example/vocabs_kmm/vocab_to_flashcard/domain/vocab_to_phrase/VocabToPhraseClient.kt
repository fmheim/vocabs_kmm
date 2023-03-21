package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatCompletionResponse


interface VocabToPhraseClient {
    suspend fun requestToGeneratePhrase(
        fromLanguage: String,
        toLanguage: String,
        fromWord: String
    ): ChatCompletionResponse
}
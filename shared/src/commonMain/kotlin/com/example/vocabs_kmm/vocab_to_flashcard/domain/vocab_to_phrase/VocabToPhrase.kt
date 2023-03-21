package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

import com.example.vocabs_kmm.core.domain.util.Resource

class VocabToPhrase(private val client: VocabToPhraseClient) {
    suspend fun execute(
        fromLanguage: String,
        toLanguage: String,
        vocab: String
    ): Resource<String> {
        return try {
            val response = client.requestToGeneratePhrase(fromLanguage, toLanguage, vocab)
            val phrase = response.choices?.firstOrNull()?.message?.content
            if(phrase.isNullOrBlank()) throw VocabToPhraseException(VocabToPhraseError.SERVER_ERROR)
            Resource.Success(phrase)
        } catch (e: VocabToPhraseException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}
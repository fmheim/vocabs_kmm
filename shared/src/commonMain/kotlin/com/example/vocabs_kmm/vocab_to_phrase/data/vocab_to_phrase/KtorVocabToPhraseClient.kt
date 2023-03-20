package com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase

import com.example.vocabs_kmm.vocab_to_phrase.domain.vocab_to_phrase.VocabToPhraseClient
import io.ktor.client.*
import io.ktor.client.request.*

class KtorVocabToPhraseClient(private val openAiHttpClient: HttpClient ) : VocabToPhraseClient{


    override suspend fun generatePhrase(
        fromLanguage: String,
        toLanguage: String,
        fromWord: String
    ): String {


        return ""
    }

}
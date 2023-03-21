package com.example.vocabs_kmm.vocab_to_flashcard.data.open_ai_model

import com.example.vocabs_kmm.vocab_to_flashcard.data.open_ai_model.dto.OpenAiModelInfo
import com.example.vocabs_kmm.vocab_to_flashcard.data.open_ai_model.dto.OpenAiModelList
import com.example.vocabs_kmm.vocab_to_flashcard.data.remote.NetworkConstants
import com.example.vocabs_kmm.vocab_to_flashcard.domain.open_ai_model.OpenAiModelClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseError
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*

class KtorOpenAiModelClient(private val openAiHttpClient: HttpClient): OpenAiModelClient {
    override suspend fun getModels(): OpenAiModelList {
        return try {
            openAiHttpClient.get {
                url(NetworkConstants.MODELS_URL)
            }.body()
        } catch(e: IOException) {
            throw VocabToPhraseException(VocabToPhraseError.SERVICE_UNAVAILABLE)
        }



    }

    override suspend fun getModelInfo(): OpenAiModelInfo {
      return OpenAiModelInfo()
    }
}
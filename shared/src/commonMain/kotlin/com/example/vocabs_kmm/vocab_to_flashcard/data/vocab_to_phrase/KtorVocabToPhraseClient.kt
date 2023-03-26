package com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase

import com.example.vocabs_kmm.vocab_to_flashcard.data.remote.NetworkConstants
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatCompletionRequest
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatCompletionResponse
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatMessage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseError
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseException
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhrasePromptProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException


class KtorVocabToPhraseClient(private val openAiHttpClient: HttpClient) : VocabToPhraseClient {
    override suspend fun requestToGeneratePhrase(
        language: String, vocab: String
    ): ChatCompletionResponse {
        val response = try {
            openAiHttpClient.post {
                url(NetworkConstants.CHAT_COMPLETION_URL)
                contentType(ContentType.Application.Json)
                setBody(
                    ChatCompletionRequest(
                        model = NetworkConstants.MODEL_GPT_3_5_TURBO, messages = arrayOf(
                            ChatMessage(
                                role = "user",
                                content = VocabToPhrasePromptProvider(
                                    language = language,
                                    vocab = vocab
                                ).prompt
                            )
                        ), maxTokens = 50
                    )
                )
            }
        } catch (e: IOException) {
            throw VocabToPhraseException(VocabToPhraseError.SERVICE_UNAVAILABLE)
        }

        checkResponseStatus(response)

        return try {
            response.body()
        } catch (e: Exception) {
            throw VocabToPhraseException(VocabToPhraseError.SERVER_ERROR)
        }
    }


    private fun checkResponseStatus(response: HttpResponse) {
        when (response.status.value) {
            in 200..299 -> Unit
            500         -> throw VocabToPhraseException(VocabToPhraseError.SERVER_ERROR)
            in 400..499 -> throw VocabToPhraseException(VocabToPhraseError.CLIENT_ERROR)
            else        -> throw VocabToPhraseException(VocabToPhraseError.UNKNOWN_ERROR)
        }
    }
}
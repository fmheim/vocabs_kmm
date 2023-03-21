package com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase

import com.example.vocabs_kmm.vocab_to_phrase.data.remote.NetworkConstants
import com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase.dto.ChatCompletionRequest
import com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase.dto.ChatCompletionResponse
import com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase.dto.ChatMessage
import com.example.vocabs_kmm.vocab_to_phrase.domain.vocab_to_phrase.VocabToPhraseClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType


class KtorVocabToPhraseClient(private val openAiHttpClient: HttpClient) : VocabToPhraseClient {
    override suspend fun generatePhrase(
        fromLanguage: String, toLanguage: String, fromWord: String
    ): String {
        return try {
            val response: ChatCompletionResponse = openAiHttpClient.post {
                url(NetworkConstants.CHAT_COMPLETION_URL)
                contentType(ContentType.Application.Json)
                setBody(
                    ChatCompletionRequest(
                        model = NetworkConstants.MODEL_GPT_3_5_TURBO,
                        messages = arrayOf(
                            ChatMessage(
                                role = "user",
                                content = "Write a concise example sentence in $toLanguage that makes the meaning of the following word clear : $fromWord. Only use $toLanguage."
                            )
                        ),
                        maxTokens = 50
                    )
                )
            }.body()
            response.choices?.firstOrNull()?.message?.content ?: throw Exception("No valid response.")
        } catch (e: Exception) {
            println("Ktor error: ${e.message}, ${e.stackTraceToString()}")
            "fail"
        }
    }
}
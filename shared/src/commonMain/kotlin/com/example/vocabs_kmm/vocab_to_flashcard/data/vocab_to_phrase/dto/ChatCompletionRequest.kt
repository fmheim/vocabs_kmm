package com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<ChatMessage>,
    @SerialName("max_tokens") val maxTokens: Int
)

@Serializable
data class ChatMessage(
    val role: String,
    val content: String
)

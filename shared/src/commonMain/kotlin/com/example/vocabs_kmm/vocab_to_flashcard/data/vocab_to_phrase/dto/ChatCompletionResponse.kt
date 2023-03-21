package com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponse(
    val id: String? = null,
    @SerialName("object") val objectType: String? = null,
    @SerialName("created") val creationDate: Long? = null,
    val model: String? = null,
    val usage: Usage? = null,
    val choices: Array<ChatChoice>? = null


)

@Serializable
data class ChatChoice(
    val message: ChatMessage,
    val index: Int,
    @SerialName("finish_reason") val finishReason: String
)

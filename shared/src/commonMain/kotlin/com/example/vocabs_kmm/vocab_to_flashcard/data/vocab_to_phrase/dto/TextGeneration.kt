package com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TextGeneration(
    val text: String? = null,
    val index: Int? = null,
    val logprobs: Int? = null,
    @SerialName("finish_reason") val finishReason: String? = null
)

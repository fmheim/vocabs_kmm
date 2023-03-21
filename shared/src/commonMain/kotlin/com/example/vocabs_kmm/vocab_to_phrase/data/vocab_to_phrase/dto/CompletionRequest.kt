package com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompletionRequest(
    @SerialName("model")  val model: String,
    @SerialName("prompt") val prompt: String,
    @SerialName("max_tokens") val maxTokens: Int,

)

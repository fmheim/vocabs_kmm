package com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Usage(
    @SerialName("prompt_tokens") val promptTokens: Int? = null,
    @SerialName("completion_tokens") val completionTokens: Int? = null,
    @SerialName("total_tokens") val totalTokens: Int? = null,

)

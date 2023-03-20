package com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CompletionResponse(
    val id: String? = null,
    @SerialName("object") val objectType: String? = null,
    @SerialName("created") val creationDate: Long? = null,
    val model: String? = null,
    val choices: List<TextGeneration>? = null,
    val usage: Usage? = null


)

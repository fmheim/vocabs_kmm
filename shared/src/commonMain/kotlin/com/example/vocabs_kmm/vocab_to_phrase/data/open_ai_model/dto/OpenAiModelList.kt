package com.example.vocabs_kmm.vocab_to_phrase.data.open_ai_model.dto

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class OpenAiModelList (
    @SerialName("data") val models: List<OpenAiModelInfo>? = null,
    @SerialName("object") val typeOfObject: String? = null,
)
package com.example.vocabs_kmm.vocab_to_flashcard.data.open_ai_model.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class OpenAiModelInfo (
    @SerialName("id") val modelId: String? = null,
    @SerialName("object") val typeOfObject: String? = null,
    @SerialName("owned_by") val modelOwner: String? = null,

    @SerialName("created") val created: Long? = null
        )
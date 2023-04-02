package com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageGenerationRequest (
    val prompt: String,
    val size: String

)
package com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageGenerationResponse(
    val created: Long,
    val data: List<ImageLink>
)

@Serializable
data class ImageLink (
    val url: String
)

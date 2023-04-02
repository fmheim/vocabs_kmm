package com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation

import com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.dto.ImageGenerationResponse

interface ImageGenerationClient {
  suspend fun generateImage(prompt: String): ImageGenerationResponse
}
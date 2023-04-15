package com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation

import com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.dto.ImageGenerationResponse
import com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.dto.ImageLink
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.ImageGenerationClient

class FakeImageGenerationClient: ImageGenerationClient {
    var imageGenerationResponse = ImageGenerationResponse(
        data = listOf(ImageLink(url = "https://www.google.com")),
        created = 1681564483
    )

    override suspend fun generateImage(prompt: String): ImageGenerationResponse {
        return imageGenerationResponse
    }


}
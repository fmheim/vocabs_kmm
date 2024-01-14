package com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation

import com.example.vocabs_kmm.core.domain.util.Resource

class GenerateImage(private val client: ImageGenerationClient) {

    suspend fun execute(prompt: String) : Resource<String> {
        return try {
            if(prompt.isBlank()) throw ImageGenerationException(ImageGenerationError.CLIENT_ERROR)
            val response = client.generateImage(prompt)
            val link = response.data.firstOrNull()?.url ?: throw ImageGenerationException(ImageGenerationError.SERVER_ERROR)
            if(link.isBlank()) throw ImageGenerationException(ImageGenerationError.SERVER_ERROR)
            Resource.Success(link)
        }catch (e: ImageGenerationException){
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}
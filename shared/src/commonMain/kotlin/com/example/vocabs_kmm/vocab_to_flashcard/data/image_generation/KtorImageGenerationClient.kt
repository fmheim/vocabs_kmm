package com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation

import com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.dto.ImageGenerationRequest
import com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.dto.ImageGenerationResponse
import com.example.vocabs_kmm.vocab_to_flashcard.data.remote.NetworkConstants
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.ImageGenerationClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.ImageGenerationError
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.ImageGenerationException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class KtorImageGenerationClient(private val openAiHttpClient: HttpClient): ImageGenerationClient {

    override suspend fun generateImage(prompt: String): ImageGenerationResponse {
        val response = try {
            openAiHttpClient.post {
                url(NetworkConstants.IMAGE_GENERATION_URL)
                contentType(ContentType.Application.Json)
                setBody(
                    ImageGenerationRequest(prompt = prompt, size = "256x256")
                )
            }
        } catch (e: IOException){
            throw ImageGenerationException(ImageGenerationError.SERVICE_UNAVAILABLE)
        }

        checkResponseStatus(response)

        return try {
            response.body()
        }catch (e: Exception){
            throw ImageGenerationException(ImageGenerationError.SERVER_ERROR)
        }
    }

    private fun checkResponseStatus(response: HttpResponse) {
        when (response.status.value) {
            in 200..299 -> Unit
            500         -> throw ImageGenerationException(ImageGenerationError.SERVER_ERROR)
            in 400..499 -> throw ImageGenerationException(ImageGenerationError.CLIENT_ERROR)
            else        -> throw ImageGenerationException(ImageGenerationError.UNKNOWN_ERROR)
        }
    }
}
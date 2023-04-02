package com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation

enum class ImageGenerationError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}
class ImageGenerationException(val error: ImageGenerationError): Exception(
    "An error occurred when generating the image: $error"
)
package com.example.vocabs_kmm.core.domain.flashcard




enum class FlashcardError {
    LANGUAGE_NOT_SPECIFIED,
    VOCAB_NOT_SPECIFIED,
    MISSING_EXAMPLE_PHRASE,
    UNKNOWN_ERROR
}

class FlashcardException(val error: FlashcardError): Exception(
    "An error occurred: $error"
)
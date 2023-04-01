package com.example.vocabs_kmm.study.domain.flashcard


enum class FlashcardError {
    N0_FLASHCARD_FOUND,
    UNKNOWN_ERROR
}

class FlashcardException(val error: FlashcardError) : Exception(
    "A Flashcard error occurred: $error"
)

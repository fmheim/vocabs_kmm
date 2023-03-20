package com.example.vocabs_kmm.vocab_to_phrase.domain.vocab_to_phrase

enum class VocabToPhraseError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class VocabToPhraseException(val error: VocabToPhraseError): Exception(
    "An error occurred when generating the phrase: $error"
)
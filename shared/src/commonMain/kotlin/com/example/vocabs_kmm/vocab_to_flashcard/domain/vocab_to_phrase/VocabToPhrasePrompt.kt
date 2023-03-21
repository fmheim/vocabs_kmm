package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

class VocabToPhrasePromptProvider(
    fromLanguage: String,
    toLanguage: String,
    vocab: String
) {
    val prompt =
        "Write a concise example sentence in $toLanguage that makes the meaning of the following $fromLanguage word clear : $vocab. Only use $toLanguage."
}
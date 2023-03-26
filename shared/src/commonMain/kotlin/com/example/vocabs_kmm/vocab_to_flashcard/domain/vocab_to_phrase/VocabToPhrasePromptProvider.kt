package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

class VocabToPhrasePromptProvider(
    language: String,
    vocab: String
) {
    val prompt =
        "Write a concise example sentence in $language that makes the meaning of the following $language word clear: $vocab. Only use $language. In the example sentence, prefix the word on which the example sentence is based with a #."}
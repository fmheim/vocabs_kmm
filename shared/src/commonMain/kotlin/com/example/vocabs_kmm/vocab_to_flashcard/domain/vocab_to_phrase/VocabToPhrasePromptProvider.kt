package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

class VocabToPhrasePromptProvider(
    language: String,
    vocab: String
) {
    val prompt =
        "Write a concise example sentence in $language that makes the meaning of the following $language word clear: $vocab. Only use $language. In the example sentence, prefix the word on which the example sentence is based with a #. The word can of course change its form so it makes sense in the sentence and the sentence is grammatically correct. The position of the word doesn't matter as long as you prefix it with a #."}

/*
Write a concise example sentence in English that makes the meaning of the following English word clear: school. Only use English. In the example sentence, prefix the word on which the example sentence is based with a #.
Then come up with a description of an image that relates to the example sentence. In your answer, add the description directly after the example sentence. Use a % as a separation of example sentence and visual description. Only write the description without any further information. Do not write something like "create an image" but just describe the image.  Feel free to add fitting styles like "digital art", "realistic" or any other art style to the description.
 */
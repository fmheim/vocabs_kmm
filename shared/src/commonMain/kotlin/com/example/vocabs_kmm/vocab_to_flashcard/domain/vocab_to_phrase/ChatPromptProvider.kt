package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

class ChatPromptProvider(
    language: String,
    vocab: String
) {
    val prompt ="Write a concise example sentence in $language that makes the meaning of the following word clear: $vocab. If the word is not $language, translate it to $language and make an example sentence for the translated word. Only use $language in the example sentence. In the example sentence, prefix the word on which the example sentence is based with a #." +
            "Based on the generated example sentence, generate a detailed description of an image, including information of the art style (e.g. digital art). This description should be English. In your answer, add the description directly after the example sentence. Use a % as a separation of example sentence and image description. Only write the description without any further information. The image should not contain any text. Limit the entire answer to 60 words."
   }

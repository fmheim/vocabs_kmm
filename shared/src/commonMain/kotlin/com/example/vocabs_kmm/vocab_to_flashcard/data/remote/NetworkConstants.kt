package com.example.vocabs_kmm.vocab_to_flashcard.data.remote

object NetworkConstants {
    private const val BASE_URL = "https://api.openai.com/v1"
    const val COMPLETIONS_URL = "$BASE_URL/completions"
    const val CHAT_COMPLETION_URL = "$BASE_URL/chat/completions"
    const val IMAGE_GENERATION_URL = "$BASE_URL/images/generations"

    const val MODELS_URL = "$BASE_URL/models"

    const val MODEL_GPT_3_5_TURBO = "gpt-3.5-turbo"
    const val MODEL_TEXT_CURIE = "text-curie-001"
    const val MODEL_GPT_4 = "gpt-4"

}
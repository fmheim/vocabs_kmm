package com.example.vocabs_kmm.vocab_to_flashcard.data.remote

object NetworkConstants {
    private const val BASE_URL = "https://api.openai.com/v1"
    val COMPLETIONS_URL = "$BASE_URL/completions"
    val CHAT_COMPLETION_URL = "$BASE_URL/chat/completions"

    val MODELS_URL = "$BASE_URL/models"

    val MODEL_GPT_3_5_TURBO = "gpt-3.5-turbo"
    val MODEL_TEXT_CURIE = "text-curie-001"
    val MODEL_GPT_4 = "gpt-4"

}
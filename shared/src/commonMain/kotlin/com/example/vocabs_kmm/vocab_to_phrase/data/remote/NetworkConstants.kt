package com.example.vocabs_kmm.vocab_to_phrase.data.remote

object NetworkConstants {
    private const val BASE_URL = "https://api.openai.com/v1"
    val COMPLETIONS_URL = "$BASE_URL/completitions"

    val MODELS_URL = "$BASE_URL/models"

}
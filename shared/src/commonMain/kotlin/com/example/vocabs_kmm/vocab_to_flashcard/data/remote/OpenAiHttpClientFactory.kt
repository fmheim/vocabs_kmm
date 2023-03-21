package com.example.vocabs_kmm.vocab_to_flashcard.data.remote

import io.ktor.client.*


expect class OpenAiHttpClientFactory {
     fun create(): HttpClient
}
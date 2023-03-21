package com.example.vocabs_kmm.vocab_to_flashcard.data.remote

import com.example.vocabs_kmm.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class OpenAiHttpClientFactory {
    actual fun create(): HttpClient {
        val apiKey = BuildConfig.OPEN_AI_KEY
        return HttpClient(Android){
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {

                json(Json{ignoreUnknownKeys})
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            apiKey, apiKey
                        )
                    }
                }
            }
        }
    }
}
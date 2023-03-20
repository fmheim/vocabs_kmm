package com.example.vocabs_kmm.vocab_to_phrase.data.remote

import com.example.vocabs_kmm.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual class OpenAiHttpClientFactory {
    actual fun create(): HttpClient {
        val apiKey = BuildConfig.OPEN_AI_KEY
        return HttpClient(Android){
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
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